package com.example.AuthService.Repository;

import com.example.AuthService.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Users, Long> {
    Optional<UserDetails> findByEmail(String email);
}
