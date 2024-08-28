package com.example.ChatService.Consumer;

import com.example.ChatService.Dto.ChatDto;
import com.example.ChatService.Service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatRequestConsumer {
    @Autowired
    ChatService chatService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "chat-queue")
    public void receiveMessage(String message) throws JsonProcessingException {
        ChatDto chatDto = objectMapper.readValue(message, ChatDto.class);
        chatService.save(chatDto);
    }
}
