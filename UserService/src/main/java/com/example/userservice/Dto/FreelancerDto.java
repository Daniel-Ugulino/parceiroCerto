package com.example.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerDto extends UserDto {
    @NotBlank
    private String descricao;

    @NotBlank
    private String especialidades;

    @NotNull
    private List<String> expreiencias;
}
