package com.dimxlp.kfrecalculator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserProfileUpsertRequest {
    @NotBlank public String userId;           // Android-side logical id (may differ from Firebase UID)
    @NotBlank public String email;
    @NotBlank public String firstName;
    @NotBlank public String lastName;
    public String fullName;
    public String profileImageUrl;
    public String role;                       // e.g., "INDIVIDUAL" | "DOCTOR" | "ADMIN"
    public String clinic;
    @NotNull public Long createdAt;           // epoch millis from Android
    @NotNull public Long lastLogin;           // epoch millis from Android
}
