package com.dimxlp.kfrecalculator.security;

import com.dimxlp.kfrecalculator.entity.User;
import com.dimxlp.kfrecalculator.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final String expectedAudience; // Firebase project id
    private final String expectedIssuer;

    public FirebaseAuthenticationFilter(UserRepository userRepository, String projectId) {
        this.userRepository = userRepository;
        this.expectedAudience = projectId;
        this.expectedIssuer = "https://securetoken.google.com/" + projectId;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();

        try {
            FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(token);

            boolean issOk = expectedIssuer.equals(decoded.getIssuer());
            Object audClaim = decoded.getClaims().get("aud");
            boolean audOk = (audClaim instanceof String s && expectedAudience.equals(s))
                || (audClaim instanceof Collection<?> c
                && c.stream().map(Object::toString).anyMatch(expectedAudience::equals));
            if (!issOk || !audOk) {
                chain.doFilter(request, response);
                return;
            }

            Map<String, Object> claims = decoded.getClaims();
            // roles could be a string or list; normalize to list
            List<String> roles = new ArrayList<>();
            Object r = claims.get("roles");
            if (r instanceof String rs) roles = List.of(rs);
            if (r instanceof Collection<?> rc) roles = rc.stream().map(Object::toString).toList();

            var authorities = roles.stream()
                .map(val -> "ROLE_" + val.toUpperCase(Locale.ROOT))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
            if (authorities.isEmpty()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_INDIVIDUAL"));
            }

            var appUser = userRepository
                .findByAuthProviderAndAuthProviderId("FIREBASE", decoded.getUid())
                .or(() -> userRepository.findByEmailIgnoreCase(decoded.getEmail()))
                .orElseGet(() -> {
                    var u = new User();
                    u.setAuthProvider("FIREBASE");
                    u.setAuthProviderId(decoded.getUid());
                    u.setEmail(decoded.getEmail());
                    return userRepository.save(u);
                });

            var auth = new UsernamePasswordAuthenticationToken(
                appUser.getId().toString(), "N/A", authorities);
            auth.setDetails(decoded);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception ignored) {
            // invalid/expired token → unauthenticated; protected endpoints will block
        }

        chain.doFilter(request, response);
    }
}
