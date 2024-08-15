package com.example.userservice.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @NotNull
    private List<String> expreiencias;
}
