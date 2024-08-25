package com.example.taskservice.Controller;

import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Dto.TaskUpdateDto;
import com.example.taskservice.Service.TaskService;
import com.example.taskservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid TaskDto taskDto) {
        try {
            Task taskEntity = taskService.save(taskDto);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Company Saved Successfully",taskEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping()
    public ResponseEntity<Object> listTasks(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam Double distance,
            @RequestParam(required = false) String provider,
            @RequestParam(required = false) Long category
    ) {
        try {
            List<Task> taskList = taskService.getTask(lat,lng,distance,provider,category);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Task List",taskList));
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
            List<Task> taskList = taskService.getByUserId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Task List",taskList));
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
            Task taskEntity = taskService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Task:",taskEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid TaskUpdateDto taskDto, @PathVariable Long id) {
        try {
            Task taskEntity = taskService.update(taskDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Company Saved Successfully",taskEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Object> activate(@PathVariable Long id) {
        try {
            taskService.activate(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Company Saved Successfully"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Object> deactivate(@PathVariable Long id) {
        try {
            taskService.deactivate(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Company Saved Successfully"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
