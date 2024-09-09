package com.example.BFF.Clients;

import com.example.BFF.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/user/{id}")
    UserDto getUser(@PathVariable("id") Long id, @RequestHeader("Cookie") String cookies);

}
