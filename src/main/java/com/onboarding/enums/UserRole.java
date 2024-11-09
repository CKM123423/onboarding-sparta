package com.onboarding.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String authorityName;

    UserRole(String authorityName) {
        this.authorityName = authorityName;
    }
}