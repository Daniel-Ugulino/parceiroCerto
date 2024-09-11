package com.example.requestservice.Domain;
import com.example.requestservice.Domain.Enum.RequestStatus;
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
    private Long taskId;

}
