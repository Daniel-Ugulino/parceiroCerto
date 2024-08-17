package com.example.userservice.Controller;

import com.example.userservice.Domain.Hirer;
import com.example.userservice.Dto.HirerDto;
import com.example.userservice.Dto.HirerUpdateDto;
import com.example.userservice.Service.HirerService;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hirer")
public class HireController {
    @Autowired
    HirerService hirerService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid HirerDto hirerDto) {
        try {
            Hirer hirerEntity = hirerService.save(hirerDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully",hirerEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        try {
            List<Hirer> companies = hirerService.listAll();
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully",companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            Hirer hirer = hirerService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("hirer Found",hirer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hirer Not Found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody @Valid HirerUpdateDto hirerUpdateDto) {
        try {
            Hirer hirerEntity = hirerService.update(hirerUpdateDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Hirer Updated",hirerEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
