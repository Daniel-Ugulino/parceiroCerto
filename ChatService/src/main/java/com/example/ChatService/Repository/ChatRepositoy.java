package com.example.ChatService.Repository;

import com.example.ChatService.Domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepositoy extends JpaRepository<Chat, Long> {
    Optional<Chat> findByTaskId(Long taskId);
}
