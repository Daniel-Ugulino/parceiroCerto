package com.example.ChatService.Controller;

import com.example.ChatService.Domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/message")
public class MessageController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(Message message){

        return message;
    }

    ///receber json como string e convertelo para uma entidade de mensagem, checar se na menssagem
    // enviada o remetente esta no chat id relacionado ao chat em questao

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public Message sendMessage(Message message) {
//
//        return message;
//    }

}
