package com.example.BFF.Clients;

import com.example.BFF.Clients.ResponseDtos.ResponseTaskDto;
import com.example.BFF.Dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "task-service")
public interface TaskServiceClient {
    @PostMapping("/task")
    ResponseTaskDto save(@RequestBody TaskDto taskDto,@RequestHeader("Cookie") String cookies);

    @GetMapping("/task/{id}")
    ResponseTaskDto getById(@PathVariable("id") Long id, @RequestHeader("Cookie") String cookies);
}
