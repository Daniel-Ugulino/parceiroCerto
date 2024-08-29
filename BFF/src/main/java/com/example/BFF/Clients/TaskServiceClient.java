package com.example.BFF.Clients;

import com.example.BFF.Clients.ResponseDtos.ResponseTaskDto;
import com.example.BFF.Dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:8080/task", name = "taskService")
public interface TaskServiceClient {
    @PostMapping()
    ResponseTaskDto save(@RequestBody TaskDto taskDto,@RequestHeader("Cookie") String cookies);

    @GetMapping("/{id}")
    ResponseTaskDto getById(@PathVariable("id") Long id, @RequestHeader("Cookie") String cookies);
}
