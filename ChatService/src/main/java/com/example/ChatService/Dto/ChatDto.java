package com.example.ChatService.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class ChatDto {
    @NotNull
    private Long hirerId;
    @NotNull
    private Long providerId;
    @NotNull
    private Long requestId;
}
