package com.example.ApiGateway.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USER,
    COMPANY,
    FREELANCE,
    HIRER;

    @Override
    public String getAuthority() {
        return name();
    }
}
