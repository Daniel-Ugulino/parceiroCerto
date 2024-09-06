package com.example.taskservice.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("parceirtoCerto-exchange");
    }

    @Bean
    public Queue chatQueue() {
        return new Queue("chat-queue", true);
    }

    @Bean
    public Queue feedbackQueue() {
        return new Queue("feedback-queue", true);
    }

    @Bean
    public Binding chatBinding() {
        return BindingBuilder.bind(chatQueue()).to(exchange()).with("chat-routing-key");
    }

    @Bean
    public Binding feedbackBinding() {
        return BindingBuilder.bind(feedbackQueue()).to(exchange()).with("feedback-routing-key");
    }
}
