package com.dimxlp.kfrecalculator.service;

import java.time.Instant;
import java.util.Locale;

import com.dimxlp.kfrecalculator.dto.UserProfileUpsertRequest;
import com.dimxlp.kfrecalculator.dto.UserProfileResponse;
import com.dimxlp.kfrecalculator.entity.User;
import com.dimxlp.kfrecalculator.enumeration.Role;
import com.dimxlp.kfrecalculator.mapper.RoleMapper;
import com.dimxlp.kfrecalculator.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository users;

    public UserServiceImpl(UserRepository users) {
        this.users = users;
    }

    @Override
    @Transactional
    public UserProfileResponse upsertSelfFromAndroid(String firebaseUid, UserProfileUpsertRequest req) {
        String provider = "FIREBASE";

        User u = users.findByAuthProviderAndAuthProviderId(provider, firebaseUid)
            .orElseGet(() -> {
                User nu = new User();
                nu.setAuthProvider(provider);
                nu.setAuthProviderId(firebaseUid);
                return nu;
            });

        // Map all incoming fields
        u.setEmail(req.email);
        u.setFirstName(req.firstName);
        u.setLastName(req.lastName);
        u.setFullName(req.fullName);
        u.setProfileImageUrl(req.profileImageUrl);
        u.setClinic(req.clinic);

        Role incoming = RoleMapper.toEnum(req.role);
        if (incoming != null) u.setRole(incoming);

        if (req.createdAt != null) {
            u.setAndroidCreatedAt(Instant.ofEpochMilli(req.createdAt));
        }
        if (req.lastLogin != null) {
            u.setAndroidLastLogin(Instant.ofEpochMilli(req.lastLogin));
        }

        User saved = users.save(u);

        var resp = new UserProfileResponse();
        resp.id = saved.getId();
        resp.email = saved.getEmail();
        resp.firstName = saved.getFirstName();
        resp.lastName = saved.getLastName();
        resp.fullName = saved.getFullName();
        resp.profileImageUrl = saved.getProfileImageUrl();
        resp.role = saved.getRole() != null ? saved.getRole() : null;
        resp.clinic = saved.getClinic();
        return resp;
    }
}
