package com.example.userservice.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@Getter
@Setter
@NoArgsConstructor
public class HirerDto extends UserDto {
    @NotBlank
    private String cpf;
}
