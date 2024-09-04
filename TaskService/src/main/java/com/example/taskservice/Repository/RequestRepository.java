package com.example.taskservice.Repository;

import com.example.taskservice.Domain.Request;
import com.example.taskservice.Domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("from Request r where r.userId = :userId")
    List<Request> findByUserId(Long userId);

    @Query("from Request r where r.task.id = :taskId")
    List<Request> findByTaskId(Long taskId);
}
