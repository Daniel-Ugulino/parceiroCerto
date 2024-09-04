package com.example.taskservice.Controller;

import com.example.taskservice.Domain.Enum.RequestStatus;
import com.example.taskservice.Domain.Request;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.RequestDto;
import com.example.taskservice.Dto.RequestUpdateDto;
import com.example.taskservice.Dto.StatusDto;
import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Service.RequestService;
import com.example.taskservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid RequestDto requestDto) {
        try {
            Request requestEntity = requestService.save(requestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request Saved Successfully",requestEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getByUserId(@PathVariable Long id) {
        try {
            List<Request> requestsList = requestService.getByUserId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request List",requestsList));
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
            Request requestEntity = requestService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request",requestEntity));
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
            List<Request> requestList = requestService.getByTaskId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request",requestList));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid RequestUpdateDto requestDto, @PathVariable Long id) {
        try {
            Request requestEntity = requestService.update(requestDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request",requestEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> changeStatus(@PathVariable Long id, @RequestBody StatusDto requestStatus) {
        try {
            Request requestEntity = requestService.changeStatus(id,requestStatus.getRequestStatus());
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request",requestEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
