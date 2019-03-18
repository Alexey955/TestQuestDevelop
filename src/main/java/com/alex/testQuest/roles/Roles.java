package com.alex.testQuest.roles;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    EMPLOYEE, CHIEF, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
