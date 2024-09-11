package com.example.requestservice.Producer;

import com.example.requestservice.Dto.ChatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatRequestProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(ChatDto chatDto) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
            "parceirtoCerto-exchange",
            "chat-routing-key",
            objectMapper.writeValueAsString(chatDto)
    );
    }
}
