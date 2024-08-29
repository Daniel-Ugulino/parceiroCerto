package com.example.BFF.Clients;

import com.example.BFF.Dto.FeedbackDto;
import com.example.BFF.Dto.RequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "http://localhost:8080/feedback", name = "feedbackService")
public interface FeedbackServiceClient {
    @PostMapping()
    RequestDto save(@RequestBody FeedbackDto requestDto, @RequestHeader("Cookie") String cookies);
}
