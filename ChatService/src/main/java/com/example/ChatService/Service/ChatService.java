package com.example.ChatService.Service;

import com.example.ChatService.Domain.Chat;
import com.example.ChatService.Dto.ChatDto;
import com.example.ChatService.Repository.ChatRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepositoy chatRepositoy;

    public Chat save(ChatDto chatDto){
        Chat chat = new Chat();
        chat.addUser(chatDto.getHirerId());
        chat.addUser(chatDto.getProviderId());
        chat.setRequestId(chatDto.getRequestId());
        chatRepositoy.save(chat);
        return chat;
    }

    public Chat getById(Long id){
        Optional<Chat> chat = chatRepositoy.findById(id);
        return chat.orElse(null);
    }

    public Chat getByOrderId(Long requestId){
        Optional<Chat> chat = chatRepositoy.findByRequestId(requestId);
        return chat.orElse(null);
    }
}
