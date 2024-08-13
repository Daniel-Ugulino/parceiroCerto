package com.example.ApiGateway.Repository;

import com.example.ApiGateway.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<Users, Long> {
    @Query("from Users u where u.email = :email")
    Users login(String email);
}
