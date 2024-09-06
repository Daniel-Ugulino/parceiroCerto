package com.example.taskservice.Producer;

import com.example.taskservice.Dto.ChatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackRequestProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(FeedbackDto feedbackDto) throws JsonProcessingException {
    amqpTemplate.convertAndSend(
            "parceirtoCerto-exchange",
            "feedback-routing-key",
            objectMapper.writeValueAsString(feedbackDto)
    );
    }
}
