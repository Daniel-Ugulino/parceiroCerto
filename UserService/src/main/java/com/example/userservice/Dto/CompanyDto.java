package com.example.userservice.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto{
    @NotBlank
    private String socialReason;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String descricao;

    @NotBlank
    private String especialidades;
}
