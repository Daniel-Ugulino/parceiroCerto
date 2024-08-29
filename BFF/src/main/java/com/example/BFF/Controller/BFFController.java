package com.example.BFF.Controller;

import com.example.BFF.Clients.FeedbackServiceClient;
import com.example.BFF.Clients.RequestServiceClient;
import com.example.BFF.Clients.TaskServiceClient;
import com.example.BFF.Clients.UserServiceClient;
import com.example.BFF.Dto.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String saveTask(@RequestBody @Valid TaskDto taskDto, HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request,"access_token");
            assert cookie != null;
            String access_token = cookie.getValue();
            UserDto userDto = userServiceClient.getUser(taskDto.getUserId());
            if(userDto.getData() != null) {
                taskServiceClient.save(taskDto,access_token);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/request")
    public String requestTask(@RequestBody @Valid RequestDto requestDto,HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request,"access_token");
            assert cookie != null;
            String access_token = cookie.getValue();
            UserDto userDto = userServiceClient.getUser(requestDto.getUserId());
            if(userDto.getData() != null) {
                requestServiceClient.save(requestDto,access_token);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/feddback")
    public String feddbackSave(@RequestBody @Valid FeedbackDto feedbackDto,HttpServletRequest request) {
        try {
            Cookie cookie = WebUtils.getCookie(request,"access_token");
            assert cookie != null;
            String access_token = cookie.getValue();
            UserDto userDto = userServiceClient.getUser(feedbackDto.getUserId());
            ResponseTaskDto responseTaskDto = taskServiceClient.getById(feedbackDto.getTaskId());
            if(userDto.getData() != null && responseTaskDto.getData() != null) {
                feedbackServiceClient.save(feedbackDto,access_token);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
