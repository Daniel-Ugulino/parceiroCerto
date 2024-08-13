package com.example.userservice.Controller;

import com.example.userservice.Dto.UserDto;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

//    @PostMapping()
//    public ResponseEntity<Object> add(@RequestBody @Valid UserDto userDto) {
//
//
//    }
}
