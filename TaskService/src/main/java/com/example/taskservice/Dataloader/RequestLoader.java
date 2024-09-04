package com.example.taskservice.Dataloader;

import com.example.taskservice.Dto.RequestDto;
import com.example.taskservice.Repository.RequestRepository;
import com.example.taskservice.Service.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Order(3)
@Component
public class RequestLoader implements ApplicationRunner {
    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (requestRepository.count() == 0) {
            String file = "src/main/java/com/example/taskservice/Dataloader/Data/request.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<RequestDto> requests = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, RequestDto.class));

            for (RequestDto requestDto : requests) {
                requestService.save(requestDto);
            }
        }
    }
}
