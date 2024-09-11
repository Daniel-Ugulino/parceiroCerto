package com.example.requestservice.Repository;

import com.example.requestservice.Domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("from Request r where r.userId = :userId")
    List<Request> findByUserId(Long userId);

    @Query("from Request r where r.taskId = :taskId")
    List<Request> findByTaskId(Long taskId);
}
