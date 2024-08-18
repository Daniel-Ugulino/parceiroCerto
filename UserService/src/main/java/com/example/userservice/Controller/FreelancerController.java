package com.example.userservice.Controller;

import com.example.userservice.Domain.Company;
import com.example.userservice.Domain.Freelancer;
import com.example.userservice.Dto.FreelancerDto;
import com.example.userservice.Dto.FreelancerUpdateDto;
import com.example.userservice.Service.FreelancerService;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    @Autowired
    FreelancerService freelancerService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid FreelancerDto freelancerDto) {
        try {
            Freelancer freelancerEntity = freelancerService.save(freelancerDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Freelancer Saved Successfully",freelancerEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("Internal server error"));
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        try {
            List<Freelancer> companies = freelancerService.listAll();
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Freelancers Found",companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>("User Not Found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            Freelancer freelancer = freelancerService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Freelancer Found",freelancer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>("Freelancer Not Found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody @Valid FreelancerUpdateDto freelancerDto) {
        try {
            Freelancer freelancerEntity = freelancerService.update(freelancerDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Freelancer Updated",freelancerEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("Internal server error"));
        }
    }

    @PatchMapping("/{id}/expertise/add")
    public ResponseEntity<Object> addExpertise(@PathVariable Long id,@RequestBody List<String> expertises) {
        try {
            Freelancer freelancerEntity = freelancerService.addExpertise(expertises,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Expertises Added",freelancerEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("Internal server error"));
        }
    }

    @PatchMapping("/{id}/expertise/remove")
    public ResponseEntity<Object> removeExpertise(@PathVariable Long id, @RequestBody List<String> expertises) {
        try {
            Freelancer freelancerEntity = freelancerService.removeExpertise(expertises,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Expertise Removed",freelancerEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("Internal server error"));
        }
    }
}
