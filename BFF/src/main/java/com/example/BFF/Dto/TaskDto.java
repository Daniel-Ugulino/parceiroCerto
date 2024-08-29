package com.example.BFF.Dto;
import com.example.BFF.Dto.Enums.Provider;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long id;
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

    private String provider;

}
