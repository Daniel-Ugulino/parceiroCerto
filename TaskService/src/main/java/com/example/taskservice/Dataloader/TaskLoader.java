package com.example.taskservice.Dataloader;

import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Repository.TaskRepository;
import com.example.taskservice.Service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Order(2)
@Component
public class TaskLoader implements ApplicationRunner {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (taskRepository.count() == 0) {
            String file = "src/main/java/com/example/taskservice/Dataloader/Data/task.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<TaskDto> tasks = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, TaskDto.class));

            for (TaskDto taskDto : tasks) {
                taskService.save(taskDto);
            }
        }
    }
}
