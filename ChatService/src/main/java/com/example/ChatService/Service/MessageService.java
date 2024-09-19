package com.example.ChatService.Service;

import com.example.ChatService.Audit.UserContext;
import com.example.ChatService.Domain.Chat;
import com.example.ChatService.Domain.Message;
import com.example.ChatService.Dto.MessageDto;
import com.example.ChatService.Repository.ChatRepositoy;
import com.example.ChatService.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepositoy chatRepositoy;

    public MessageDto save(MessageDto messageDto) throws Exception {
        System.out.println(messageDto.getChatId());
        Optional<Chat> chat = chatRepositoy.findById(messageDto.getChatId());
        if(chat.isPresent()) {
            Message message = new Message();
            message.setSender(Long.valueOf(UserContext.getUserId()));
            message.setChat(chat.get());
            message.setContent(messageDto.getContent());
            messageRepository.save(message);
            messageDto.setCreatedAt(message.getCreatedAt());
            return messageDto;
        }else {
            throw new Exception("Chat not found");
        }
    }
}
