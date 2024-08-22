package com.example.taskservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestUpdateDto {
    @NotBlank
    private String description;
    @NotNull
    private Double amount;
    @NotBlank
    private String notes;
}
