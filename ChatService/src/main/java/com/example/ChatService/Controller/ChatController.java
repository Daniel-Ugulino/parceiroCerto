package com.example.ChatService.Controller;

import com.example.ChatService.Domain.Chat;
import com.example.ChatService.Service.ChatService;
import com.example.ChatService.Utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBychatId(@PathVariable Long id) {
        try {
            Chat chat = chatService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Chat",chat));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Object> getByRequestId(@PathVariable Long id) {
        try {
            Chat chat = chatService.getByOrderId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Chat",chat));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }


}
