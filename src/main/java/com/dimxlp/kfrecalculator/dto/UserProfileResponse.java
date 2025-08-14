package com.dimxlp.kfrecalculator.dto;

import java.util.UUID;

import com.dimxlp.kfrecalculator.enumeration.Role;

public class UserProfileResponse {
    public UUID id;           // backend UUID
    public String email;
    public String firstName;
    public String lastName;
    public String fullName;
    public String profileImageUrl;
    public Role role;
    public String clinic;
}
