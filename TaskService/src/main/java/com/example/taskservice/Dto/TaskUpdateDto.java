package com.example.taskservice.Dto;

import com.example.taskservice.Domain.Category;
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
public class TaskUpdateDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Long categoryId;
    @NotNull
    private Float price;
}
