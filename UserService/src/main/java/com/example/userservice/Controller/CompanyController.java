package com.example.userservice.Controller;

import com.example.userservice.Domain.Company;
import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.CompanyUpdateDto;
import com.example.userservice.Service.CompanyService;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid CompanyDto companyDto) {
        try {
            Company companyEntity = companyService.save(companyDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully",companyEntity));
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
            List<Company> companies = companyService.listAll();
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully",companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            Company company = companyService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Company Found",company));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody @Valid CompanyUpdateDto companyDto) {
        try {
            Company companyEntity = companyService.update(companyDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Company Updated",companyEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> addExpertise(@PathVariable Long id,@RequestBody List<String> expertises) {
        try {
            Company companyEntity = companyService.addExpertise(expertises,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Expertises Added",companyEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PatchMapping("/{id}/{index}")
    public ResponseEntity<Object> removeExpertise(@PathVariable Long id, @PathVariable Integer index) {
        try {
            Company companyEntity = companyService.removeExpertise(index,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Expertise Removed",companyEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
