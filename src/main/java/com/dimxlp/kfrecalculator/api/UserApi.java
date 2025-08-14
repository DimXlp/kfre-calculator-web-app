package com.dimxlp.kfrecalculator.api;

import com.dimxlp.kfrecalculator.dto.UserProfileUpsertRequest;
import com.dimxlp.kfrecalculator.dto.UserProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @PutMapping("/self")
    UserProfileResponse upsertSelf(@Valid @RequestBody UserProfileUpsertRequest req,
                                   Authentication auth);
}
