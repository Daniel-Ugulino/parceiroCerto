package com.example.FeedbackService.Controller;

import com.example.FeedbackService.Domain.Feedback;
import com.example.FeedbackService.Dto.FeedbackDto;
import com.example.FeedbackService.Service.FeedbackService;
import com.example.FeedbackService.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody @Valid FeedbackDto feedbackDto) {
        try {
            Feedback feedbackEntity = feedbackService.update(feedbackDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request Saved Successfully",feedbackEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<Object> getByRequestId(@PathVariable Long id) {
        try {
            Feedback feedback = feedbackService.getByRequestId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request List",feedback));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Object> getByTaskId(@PathVariable Long id) {
        try {
            List<Feedback> feedbackList = feedbackService.getByTaskId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request List",feedbackList));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Feedback feedbackEntity = feedbackService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request",feedbackEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
