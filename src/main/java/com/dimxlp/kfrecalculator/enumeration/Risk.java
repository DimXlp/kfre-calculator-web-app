package com.dimxlp.kfrecalculator.enumeration;

import lombok.Getter;

@Getter // Lombok will generate the getter for us.
public enum Risk {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    UNKNOWN("Unknown");

    private final String displayName;

    Risk(String displayName) {
        this.displayName = displayName;
    }

    // The @Getter annotation from Lombok makes the getDisplayName() method.
    // No need to write it manually.
}