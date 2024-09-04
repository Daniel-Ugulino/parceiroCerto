package com.example.FeedbackService.Consumer;

import com.example.FeedbackService.Dto.FeedbackConsumerDto;
import com.example.FeedbackService.Dto.FeedbackDto;
import com.example.FeedbackService.Service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackRequestConsumer {
    @Autowired
    FeedbackService feedbackService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "feedback-queue")
    public void receiveMessage(String message) throws Exception {
        System.out.println(message);
        FeedbackConsumerDto feedbackConsumerDto = objectMapper.readValue(message, FeedbackConsumerDto.class);
        feedbackService.save(feedbackConsumerDto);
    }
}
