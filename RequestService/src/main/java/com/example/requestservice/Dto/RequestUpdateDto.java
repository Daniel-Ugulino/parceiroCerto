package com.example.requestservice.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String notes;
}
