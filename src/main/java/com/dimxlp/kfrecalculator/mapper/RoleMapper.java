package com.dimxlp.kfrecalculator.mapper;

import com.dimxlp.kfrecalculator.enumeration.Role;
import java.util.Locale;

/** Maps external role strings to internal Role enum and back. */
public final class RoleMapper {
    private RoleMapper() {}

    public static Role toEnum(String raw) {
        if (raw == null) return null;
        String s = raw.trim().toUpperCase(Locale.ROOT);
        if (s.startsWith("ROLE_")) s = s.substring(5);
        return switch (s) {
            case "DOCTOR" -> Role.ROLE_DOCTOR;
            case "ADMIN" -> Role.ROLE_ADMIN;
            case "INDIVIDUAL" -> Role.ROLE_INDIVIDUAL;
            default -> null;
        };
    }

    public static String toExternal(Role role) {
        if (role == null) return null;
        return switch (role) {
            case ROLE_DOCTOR -> "DOCTOR";
            case ROLE_ADMIN -> "ADMIN";
            default -> "INDIVIDUAL";
        };
    }
}
