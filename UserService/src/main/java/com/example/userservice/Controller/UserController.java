package com.example.userservice.Controller;
import com.example.userservice.Service.UserService;
import com.example.userservice.Utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<Object> resetPassword(@PathVariable Long id, @RequestBody String password) {
        try {
            userService.resetPassword(password,id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully"));
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
