package com.example.taskservice.Domain;

import com.example.taskservice.Domain.Enum.RequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.CREATED;
    private Integer amount;
    private Float totalPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "task_id")
    private Task task;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        if (this.task != null && this.amount != null && this.task.getPrice() != null) {
            this.totalPrice = this.amount * this.task.getPrice();
        }
        else {
            this.totalPrice = 0F;
        }
    }
}
