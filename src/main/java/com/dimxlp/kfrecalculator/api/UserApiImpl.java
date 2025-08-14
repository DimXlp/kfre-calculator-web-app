package com.dimxlp.kfrecalculator.api;

import com.dimxlp.kfrecalculator.service.UserService;
import com.dimxlp.kfrecalculator.dto.UserProfileUpsertRequest;
import com.dimxlp.kfrecalculator.dto.UserProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiImpl implements UserApi {

    private final UserService userService;

    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserProfileResponse upsertSelf(UserProfileUpsertRequest req, Authentication auth) {
        String firebaseUid = String.valueOf(auth.getPrincipal());
        return userService.upsertSelfFromAndroid(firebaseUid, req);
    }
}
