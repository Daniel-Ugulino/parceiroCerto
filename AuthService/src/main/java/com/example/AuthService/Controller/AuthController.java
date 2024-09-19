package com.example.AuthService.Controller;

import com.example.AuthService.Dto.LoginDto;
import com.example.AuthService.Dto.UserDto;
import com.example.AuthService.Service.AuthService;
import com.example.AuthService.Utils.CustomResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/logout")
    ResponseEntity<Object> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("access_token","");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0); // 10 minutes
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User Logged out"));
    }

    @GetMapping("/refresh-token")
    ResponseEntity<Object> refreshToken(HttpServletResponse response, HttpServletRequest request) {
        UserDto user = authService.refreshToken(response,request);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User Logged in", user));
    }
}
