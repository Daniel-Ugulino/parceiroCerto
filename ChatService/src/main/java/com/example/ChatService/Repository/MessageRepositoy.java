package com.example.ChatService.Repository;

import com.example.ChatService.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepositoy extends JpaRepository<Message, Long> {
}
