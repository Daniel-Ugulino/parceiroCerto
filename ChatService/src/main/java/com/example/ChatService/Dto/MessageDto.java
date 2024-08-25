package com.example.ChatService.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
public class MessageDto {
    @NotEmpty
    private String content;

    @NotNull
    private Long sender;

    @NotNull
    private Long chatId;
}
