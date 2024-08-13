package com.example.userservice.Repository;

import com.example.userservice.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("from Users u where u.email = :email")
    Users login(String email);
}
