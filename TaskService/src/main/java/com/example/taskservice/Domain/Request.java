package com.example.taskservice.Domain;

import com.example.taskservice.Domain.Enum.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request")
@Audited
@AuditTable(value = "request_audit", schema = "audit")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long userId;
    private String notes;
    private RequestStatus status = RequestStatus.CREATED;
    private Integer amount;
    private Double totalPrice = 0D;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        if (task != null && amount != null && task.getPrice() != null) {
            this.totalPrice = amount * task.getPrice();
        }
    }
}
