package com.example.ChatService.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable(value = "chat_audit", schema = "audit")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @ElementCollection
    @CollectionTable(name = "chat_users", joinColumns = @JoinColumn(name = "chat_id"))
    private List<Long> users = new ArrayList<>(2);

    private Long requestId;
    private Boolean status = true;

    public void addUser(Long userId) {
        if (this.users.size() >= 2) {
            throw new IllegalArgumentException("Cannot add more than 2 users to a chat.");
        }
        this.users.add(userId);
    }
}
