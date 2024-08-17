package com.example.userservice.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto extends UserDto{
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
