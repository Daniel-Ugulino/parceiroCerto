package com.example.ChatService.Controller;

import com.example.ChatService.Dto.MessageDto;
import com.example.ChatService.Service.MessageService;
import com.example.ChatService.Utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping()
    public ResponseEntity<Object> sendMessage(MessageDto messageDto){
        try {
            MessageDto messageEntity = messageService.save(messageDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Message Sent Successfully",messageEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
