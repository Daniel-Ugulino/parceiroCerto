package com.example.ApiGateway.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Roles role;
    private String gender;
    private Date birthday;
    private Date createdAt;
    private boolean enabled = true;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}

