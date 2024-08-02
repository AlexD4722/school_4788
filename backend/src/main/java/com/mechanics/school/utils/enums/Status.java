package com.mechanics.school.utils.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Status {
    INACTIVE(0),
    ACTIVE(1);
    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}