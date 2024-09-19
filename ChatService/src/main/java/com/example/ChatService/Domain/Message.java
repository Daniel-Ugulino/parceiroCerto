package com.example.ChatService.Domain;

import com.example.ChatService.Audit.UserContext;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable(value = "message_audit", schema = "audit")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long sender;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
