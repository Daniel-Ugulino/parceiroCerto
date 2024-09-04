package com.example.taskservice.Producer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class FeedbackDto {
    @NotNull
    private Long hirerId;
    @NotNull
    private Long taskId;
    @NotNull
    private Long requestId;
}

