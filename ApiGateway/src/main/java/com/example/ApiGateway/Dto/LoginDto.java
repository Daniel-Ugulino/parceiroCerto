package com.example.ApiGateway.Dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @Valid
    private String email;

    @Valid
    private String password;
}
