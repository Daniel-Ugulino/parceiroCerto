package com.example.ApiGateway.Controller;

import com.example.ApiGateway.Dto.LoginDto;
import com.example.ApiGateway.Dto.UserDto;
import com.example.ApiGateway.Service.AuthService;
import com.example.ApiGateway.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

//    @PostMapping("/login")
//    ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) {
//        try {
//            UserDto user = authService.login(loginDto,response);
//            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User Logged in", user));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("An error occurred while login in"));
//        }
//    }

}
