package com.example.requestservice.Producer;

import com.example.requestservice.Dto.FeedbackDto;
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
