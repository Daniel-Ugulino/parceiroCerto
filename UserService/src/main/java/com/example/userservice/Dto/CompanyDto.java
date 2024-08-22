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
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "CNPJ is not valid")
    private String cnpj;

    @NotBlank
    private String description;

    @NotBlank
    private String professionalField;

    @NotNull
    private List<String> expertise;
}
