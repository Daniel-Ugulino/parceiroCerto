package com.example.taskservice.Dto;

import com.example.taskservice.Domain.Enum.RequestStatus;
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
    private Double amount;
    @NotBlank
    private String notes;
}
