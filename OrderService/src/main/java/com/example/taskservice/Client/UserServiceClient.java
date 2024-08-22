package com.example.taskservice.Client;

import com.example.taskservice.Client.ResponseDto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8082", name = "usersService")
public interface UserServiceClient {

    @GetMapping("/user/{id}")
    UserDto getUser(@PathVariable("id") Long id);

}
