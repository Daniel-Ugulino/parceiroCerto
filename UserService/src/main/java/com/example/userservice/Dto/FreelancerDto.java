package com.example.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerDto {
    @NotBlank
    private String descricao;

    @NotBlank
    private String especialidades;

    @NotBlank
    private String expreiencias;
}
