package com.example.BFF.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class FeedbackDto {
    @NotNull
    private Long userId;

    @NotBlank
    private String comments;

    @Min(0)
    @Max(10)
    @NotNull
    private Integer grade;

    @NotNull
    private Long taskId;
}
