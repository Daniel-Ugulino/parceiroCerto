package com.example.ChatService.Repository;

import com.example.ChatService.Domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
