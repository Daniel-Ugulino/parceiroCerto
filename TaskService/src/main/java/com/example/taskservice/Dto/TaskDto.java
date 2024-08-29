package com.example.taskservice.Dto;

import com.example.taskservice.Domain.Enum.Provider;
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
public class TaskDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
    @NotNull
    private Double price;
    @NotBlank
    private String provider;

}
