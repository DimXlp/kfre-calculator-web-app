package com.dimxlp.kfrecalculator.service;

import com.dimxlp.kfrecalculator.dto.UserProfileUpsertRequest;
import com.dimxlp.kfrecalculator.dto.UserProfileResponse;

public interface UserService {
    UserProfileResponse upsertSelfFromAndroid(String firebaseUid, UserProfileUpsertRequest req);
}
