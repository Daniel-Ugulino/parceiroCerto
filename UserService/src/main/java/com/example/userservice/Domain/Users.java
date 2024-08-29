package com.example.userservice.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable(value = "users_audit", schema = "audit")
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    private String password;
    @Column(unique=true)
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String gender;
    private Date birthday;
    @JsonIgnore
    private Date createdAt;
    private Boolean enabled = true;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_location")
    private Location location;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    public void reset(Users users){
        this.setId(users.getId());
        this.setPassword(users.getPassword());
        this.setRole(users.getRole());
        this.setEnabled(users.getEnabled());
        this.setCreatedAt(users.getCreatedAt());
        this.setEmail(users.getEmail());
    }

}
