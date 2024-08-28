package com.example.ChatService.Repository;

import com.example.ChatService.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
//    @Query("from Message m where m.chat = :userId")
//    List<Message> findByChatId(Long chatId);
}
