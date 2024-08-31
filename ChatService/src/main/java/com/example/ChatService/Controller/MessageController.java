package com.example.ChatService.Controller;

import com.example.ChatService.Domain.Message;
import com.example.ChatService.Dto.ChatDto;
import com.example.ChatService.Dto.MessageDto;
import com.example.ChatService.Dto.UserDto;
import com.example.ChatService.Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/register")
    @SendTo("/topic/message")
    public UserDto enterChat(String messageString, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
        UserDto userDto = objectMapper.readValue(messageString, UserDto.class);
        headerAccessor.getSessionAttributes().put("username", userDto.getSender());
        return userDto;
    }

    @MessageMapping("send")
    @SendTo("/topic/public")
    public String sendMessage(String messageString, SimpMessageHeaderAccessor headerAccessor) throws JsonProcessingException {
        MessageDto messageDto = objectMapper.readValue(messageString, MessageDto.class);
        messageService.save(messageDto);
        return messageDto.getContent();
    }
}
