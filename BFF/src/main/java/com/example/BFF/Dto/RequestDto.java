package com.example.BFF.Dto;

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
public class RequestDto {
    @NotBlank
    private String description;
    @NotNull
    private Long userId;
    @NotNull
    private Long taskId;
    @NotNull
    private Integer amount;
    @NotBlank
    private String notes;
}
