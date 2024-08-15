package com.example.userservice.Controller;
import com.example.userservice.Domain.Users;

import com.example.userservice.Dto.UserDto;
import com.example.userservice.Dto.UserUpdateDto;
import com.example.userservice.Service.UserService;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid UserDto userDto) {
        try {
            Users usersModel = new Users();
            BeanUtils.copyProperties(userDto, usersModel);
            Users savedUser = userService.save(usersModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse<>("User added successfully", savedUser));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("An error occurred while saving the user"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            Users user = userService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User Found", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>("User Not Found"));
        }
    }

    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<Object> resetPassword(@PathVariable Long id, @RequestBody String password) {
        try {
            userService.resetPassword(password,id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        try {
            Users usersModel = new Users();
            BeanUtils.copyProperties(userDto, usersModel);
            Users updatedUser = userService.update(usersModel,id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse<>("User updated successfully", updatedUser));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("An error occurred while updating the user"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("An error occurred deleting the user"));

        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Object> activate(@PathVariable Long id) {
        try {
            userService.activate(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User activated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("An error occurred while updating the user"));
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Object> deactivate(@PathVariable Long id) {
        try {
            userService.deactivate(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("User deactivated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>("An error occurred while updating the user"));
        }
    }
}
