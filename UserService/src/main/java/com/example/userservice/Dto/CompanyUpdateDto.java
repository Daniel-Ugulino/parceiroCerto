package com.example.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyUpdateDto extends UserUpdateDto{
    @NotBlank
    private String socialName;

    @NotBlank
    private String description;

    @NotBlank
    private String professionalField;
}
