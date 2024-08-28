package com.example.ChatService.Repository;

import com.example.ChatService.Domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRepositoy extends JpaRepository<Chat, Long> {
    @Query("from Chat ch where ch.requestId = :requestId")
    Optional<Chat> findByRequestId(Long requestId);
}
