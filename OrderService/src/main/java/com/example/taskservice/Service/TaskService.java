package com.example.taskservice.Service;

import com.example.taskservice.Client.ResponseDto.Roles;
import com.example.taskservice.Client.ResponseDto.UserDto;
import com.example.taskservice.Client.UserServiceClient;
import com.example.taskservice.Domain.Enum.Provider;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Dto.TaskUpdateDto;
import com.example.taskservice.Repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserServiceClient userServiceClient;

    public List<Task> getTask(double lat, double lng, Double distanceRange, String providerType, Long categoryId){
        return taskRepository.findTasksByFilters(providerType,categoryId,lat,lng,distanceRange);
    }

    public Task getById(Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return task.get();
        }else {
            throw new Exception("Task not found");
        }
    }

    public List<Task> getByUserId(Long id) throws Exception{
        return taskRepository.findByUserId(id);
    }

    public Task save(TaskDto taskDto) throws Exception{
        UserDto user = userServiceClient.getUser(taskDto.getUserId());
        if(user.getData().getEnabled()) {
            Task taskEntity = new Task();
            BeanUtils.copyProperties(taskDto, taskEntity);
            if(user.getData().getRole() == Roles.COMPANY){
                taskEntity.setProviderType(Provider.COMPANY);
            }else{
                taskEntity.setProviderType(Provider.FREELANCER);
            }
            taskRepository.save(taskEntity);
            return taskEntity;
        }else{
            throw new Exception("User not enabled");
        }
    }

    public Task update(TaskUpdateDto taskDto, Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Task storedTask = task.get();
            BeanUtils.copyProperties(taskDto, storedTask, "id", "feedbacks", "requests");
            taskRepository.save(storedTask);
            return storedTask;
        }else {
            throw new Exception("Task not found");
        }
    }

    public void deactivate(Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Task taskEntity = task.get();
            taskEntity.setActive(false);
            taskRepository.save(taskEntity);
        }else {
            throw new Exception("Task not found");
        }
    }

    public void activate(Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Task taskEntity = task.get();
            taskEntity.setActive(true);
            taskRepository.save(taskEntity);
        }else {
            throw new Exception("Task not found");
        }
    }

}
