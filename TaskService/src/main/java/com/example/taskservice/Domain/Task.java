package com.example.taskservice.Domain;
import com.example.taskservice.Domain.Enum.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
@Audited
@AuditTable(value = "task_audit", schema = "audit")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Float price;
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @Enumerated(EnumType.STRING)
    private Provider providerType;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests;

    private Boolean active = true;
}
