package com.example.userservice.Controller;

import com.example.userservice.Domain.Location;
import com.example.userservice.Dto.LocationDto;
import com.example.userservice.Service.LocationService;
import com.example.userservice.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping("/{id}")
    public ResponseEntity<Object> add(@RequestBody @Valid LocationDto locationDto, @PathVariable Long id) {
        try {
            Location locationEntity = locationService.save(locationDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Location Saved Successfully",locationEntity));
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
            List<Location> companies = locationService.listAll();
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Locations Found",companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            Location location = locationService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Location Found",location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location Not Found");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getByUser(@PathVariable Long id) {
        try {
            Location location = locationService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Location Found",location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location Not Found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid LocationDto locationDto, @PathVariable Long id) {
        try {
            Location locationEntity = locationService.update(locationDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Password reset successfully",locationEntity));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("Data integrity violation"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
