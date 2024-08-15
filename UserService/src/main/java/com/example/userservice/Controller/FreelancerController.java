package com.example.userservice.Controller;

import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.UserDto;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid CompanyDto companyDto) {

    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody @Valid CompanyDto companyDto) {

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> addExpertise(@PathVariable Long id,@RequestBody List<String> expertises) {

    }

    @PatchMapping("/{id}/{index}")
    public ResponseEntity<Object> removeExpertise(@PathVariable Long id, @PathVariable Long index) {

    }
}
