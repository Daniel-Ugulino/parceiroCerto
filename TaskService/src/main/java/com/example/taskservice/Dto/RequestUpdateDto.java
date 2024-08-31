package com.example.taskservice.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class RequestUpdateDto {
    @NotBlank
    private String description;
    @NotNull
    private Integer amount;
    @NotBlank
    private String notes;
}
