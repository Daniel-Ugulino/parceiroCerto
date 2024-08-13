package com.example.userservice.Repository;

import com.example.userservice.Domain.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
}
