package com.example.userservice.Repository;

import com.example.userservice.Domain.Hirer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HirerRepository extends JpaRepository<Hirer, Long> {
}
