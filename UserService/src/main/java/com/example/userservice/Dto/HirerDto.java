package com.example.userservice.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public class HirerDto extends UserDto {
    @NotBlank
    private String cpf;
}
