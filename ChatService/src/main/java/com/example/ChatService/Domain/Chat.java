package com.example.ChatService.Domain;

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
@AuditTable(value = "chat_audit", schema = "audit")
@Table(name = "chat")
@Inheritance(strategy = InheritanceType.JOINED)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Date createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
