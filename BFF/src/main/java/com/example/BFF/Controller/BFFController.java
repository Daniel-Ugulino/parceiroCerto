package com.example.BFF.Controller;

import com.example.BFF.Clients.FeedbackServiceClient;
import com.example.BFF.Clients.RequestServiceClient;
import com.example.BFF.Clients.ResponseDtos.ResponseFeedbackDto;
import com.example.BFF.Clients.ResponseDtos.ResponseRequestDto;
import com.example.BFF.Clients.ResponseDtos.ResponseTaskDto;
import com.example.BFF.Clients.TaskServiceClient;
import com.example.BFF.Clients.UserServiceClient;
import com.example.BFF.Dto.*;
import com.example.BFF.Dto.Enums.Provider;
import com.example.BFF.Dto.Enums.Roles;
import com.example.BFF.Utils.CustomResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@RestController
@RequestMapping("/bff")
public class BFFController {
    @Autowired
    RequestServiceClient requestServiceClient;

    @Autowired
    TaskServiceClient taskServiceClient;

    @Autowired
    FeedbackServiceClient feedbackServiceClient;

    @Autowired
    UserServiceClient userServiceClient;


    @PostMapping("/task")
    public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskDto taskDto, HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request,"access_token");
            assert cookie != null;
            String access_token = cookie.getValue();
            UserDto userDto = userServiceClient.getUser(taskDto.getUserId());
            if(userDto.getData() != null) {
                if(userDto.getData().getRole() == Roles.COMPANY){
                    taskDto.setProvider(Provider.COMPANY.name());
                }else if (userDto.getData().getRole() == Roles.FREELANCER){
                    taskDto.setProvider(Provider.FREELANCER.name());
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>("User not Allowed to create Task"));
                }
                ResponseTaskDto taskResponse =  taskServiceClient.save(taskDto,access_token);
                return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Task Saved Successfully",taskResponse.getData()));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/request")
    public ResponseEntity<Object> requestTask(@RequestBody @Valid RequestDto requestDto,HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request,"access_token");
            assert cookie != null;
            String access_token = cookie.getValue();
            UserDto userDto = userServiceClient.getUser(requestDto.getUserId());
            ResponseTaskDto responseTaskDto = taskServiceClient.getById(requestDto.getTaskId(),access_token);
            if(userDto.getData() != null && responseTaskDto.getData() != null) {
                ResponseRequestDto requestResponse = requestServiceClient.save(requestDto,access_token);
                requestResponse.getData().setTaskId(requestDto.getTaskId());
                return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Request Saved Successfully",requestResponse.getData()));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/feedback")
    public ResponseEntity<Object> feddbackSave(@RequestBody @Valid FeedbackDto feedbackDto,HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request,"access_token");
            assert cookie != null;
            String access_token = cookie.getValue();
            UserDto userDto = userServiceClient.getUser(feedbackDto.getUserId());
            ResponseTaskDto responseTaskDto = taskServiceClient.getById(feedbackDto.getTaskId(),access_token);
            if(userDto.getData() != null && responseTaskDto.getData() != null) {
                ResponseFeedbackDto feedbackResponse = feedbackServiceClient.save(feedbackDto,access_token);
                return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>("Feedback Saved Successfully",feedbackResponse.getData()));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
