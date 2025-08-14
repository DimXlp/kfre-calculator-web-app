package com.dimxlp.kfrecalculator.api;

import com.dimxlp.kfrecalculator.dto.AccountResponse;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountApiImpl implements AccountApi {

    @Override
    public AccountResponse getCurrentUserInfo(Authentication auth) {
        if (auth == null) return new AccountResponse(null, null, List.of(), "NO_AUTH_IN_CONTROLLER");
        String principal = String.valueOf(auth.getPrincipal());
        FirebaseToken token = (auth.getDetails() instanceof FirebaseToken t) ? t : null;
        String email = token != null ? token.getEmail() : null;
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return new AccountResponse(principal, email, roles, "OK");
    }
}
