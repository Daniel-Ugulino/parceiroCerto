package com.example.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompanyUpdateDto extends UserUpdateDto{
    @NotBlank
    private String socialName;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String description;

    @NotBlank
    private String professionalField;

    @NotNull
    private List<String> expertise;
}
