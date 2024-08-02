package com.mechanics.school.utils.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Gender {
    FEMALE(0),
    MALE(1),
    OTHER(2);
    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

