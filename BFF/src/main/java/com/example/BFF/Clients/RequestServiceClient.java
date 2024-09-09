package com.example.BFF.Clients;

import com.example.BFF.Clients.ResponseDtos.ResponseRequestDto;
import com.example.BFF.Dto.RequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "task-service")
public interface RequestServiceClient {
    @PostMapping("/request")
    ResponseRequestDto save(@RequestBody RequestDto requestDto, @RequestHeader("Cookie") String cookies);
}
