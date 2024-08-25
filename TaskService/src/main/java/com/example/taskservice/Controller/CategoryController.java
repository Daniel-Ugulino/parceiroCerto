package com.example.taskservice.Controller;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Dto.CategoryDto;
import com.example.taskservice.Service.CategoryService;
import com.example.taskservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid CategoryDto categoryDto) {
        try {
            Category categoryEntity = categoryService.save(categoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Category Saved Successfully",categoryEntity));
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
            List<Category> companies = categoryService.listAll();
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Categories Found",companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid CategoryDto categoryDto, @PathVariable Long id) {
        try {
            Category categoryEntity = categoryService.update(categoryDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Category updated",categoryEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Category deleted"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
