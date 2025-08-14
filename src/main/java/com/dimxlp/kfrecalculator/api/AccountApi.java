package com.dimxlp.kfrecalculator.api;

import com.dimxlp.kfrecalculator.dto.AccountResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/account")
public interface AccountApi {

    @GetMapping("/current")
    AccountResponse getCurrentUserInfo(Authentication auth);
}
