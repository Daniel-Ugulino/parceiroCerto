package com.example.taskservice.Repository;

import com.example.taskservice.Domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("from Task t where t.userId = :userId")
    List<Task> findByUserId(Long userId);


    @Query(value = "SELECT t.* FROM task t "
            + "JOIN users u ON t.user_id = u.id "
            + "JOIN location l ON u.id_location = l.id "
            + "WHERE "
            + "(t.provider_type = :providerType OR :providerType IS NULL) "
            + "AND (t.id_category = :categoryId OR :categoryId IS NULL) "
            + "AND ST_Distance( "
            + "    ST_SetSRID(ST_MakePoint(l.lng, l.lat), 4326)::geography, "
            + "    ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)::geography "
            + ") <= :distanceRange",
            nativeQuery = true)
    List<Task> findTasksByFilters(
            @Param("providerType") String providerType,
            @Param("categoryId") Long categoryId,
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("distanceRange") Double distanceRange
    );
}
