package com.example.BFF.Clients.ResponseDtos;

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
public class ResponseFeedbackDto {
    private data data;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class data {
        private Long id;
        private Long userId;
        private String comments;
        private Integer grade;
        private Long taskId;
    }
}
