package com.example.FeedbackService.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class FeedbackConsumerDto {
    @NotNull
    private Long hirerId;

    @NotNull
    private Long taskId;

    @NotNull
    private Long requestId;
}
