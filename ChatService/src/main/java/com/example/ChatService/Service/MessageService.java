package com.example.ChatService.Service;

import com.example.ChatService.Dto.MessageDto;
import com.example.ChatService.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

//    public MessageDto save(MessageDto messageDto) {
//
//    }
}
