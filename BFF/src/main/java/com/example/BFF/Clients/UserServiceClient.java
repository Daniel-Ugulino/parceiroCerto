package com.example.BFF.Clients;

import com.example.BFF.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/user", name = "usersService")
public interface UserServiceClient {
    @GetMapping("/{id}")
    UserDto getUser(@PathVariable("id") Long id);

}
