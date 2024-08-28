package com.example.AuthService.Controller;

import com.example.AuthService.Dto.LoginDto;
import com.example.AuthService.Dto.UserDto;
import com.example.AuthService.Service.AuthService;
import com.example.AuthService.Utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) {
            UserDto user = authService.login(loginDto,response);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User Logged in", user));
    }

    @GetMapping("/refresh-token")
    ResponseEntity<Object> refreshToken(HttpServletResponse response, HttpServletRequest request) {
        UserDto user = authService.refreshToken(response,request);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User Logged in", user));
    }
}
