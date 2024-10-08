package com.example.taskservice.Dto;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Domain.Enum.Provider;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDto {
    @Id
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Long userId;
    @NotNull
    private Category category;
    @NotNull
    private Float price;
    @NotBlank
    private Provider providerType;
    @NotBlank
    private Boolean active;
}
