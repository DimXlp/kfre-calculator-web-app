package com.dimxlp.kfrecalculator.security;

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

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private final String expectedAudience; // Firebase project id
    private final String expectedIssuer;

    public FirebaseAuthenticationFilter(String projectId) {
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

            var authorities = Set.of(new SimpleGrantedAuthority("ROLE_INDIVIDUAL"));
            var auth = new UsernamePasswordAuthenticationToken(decoded.getUid(), "N/A", authorities);
            auth.setDetails(decoded);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception ignored) {
            // keep unauthenticated if verification fails
        }

        chain.doFilter(request, response);
    }
}
