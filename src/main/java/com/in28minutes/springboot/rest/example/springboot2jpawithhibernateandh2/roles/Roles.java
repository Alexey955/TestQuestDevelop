package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.roles;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    EMPLOYEE, CHIEF, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
