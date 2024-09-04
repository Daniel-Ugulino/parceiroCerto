package com.example.taskservice.Producer;

import com.example.taskservice.Dto.ChatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatRequestProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(ChatDto chatDto) throws JsonProcessingException {
    amqpTemplate.convertAndSend(
            "chat-exchange",
            "chat-routing-key",
            objectMapper.writeValueAsString(chatDto)
    );
    }
}
