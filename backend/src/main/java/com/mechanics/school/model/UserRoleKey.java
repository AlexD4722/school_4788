package com.mechanics.school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserRoleKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "role_id")
    Long roleId;

    // getters, setters, hashCode, equals
}
