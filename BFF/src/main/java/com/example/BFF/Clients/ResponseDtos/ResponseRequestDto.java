package com.example.BFF.Clients.ResponseDtos;

import com.example.BFF.Dto.Enums.RequestStatus;
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
public class ResponseRequestDto {
    private data data;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class data {
        private Long id;
        private String description;
        private Long userId;
        private Long taskId;
        private Integer amount;
        private String notes;
        private RequestStatus status;
        private Double totalPrice;
    }
}
