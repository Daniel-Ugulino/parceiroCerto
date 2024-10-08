package com.example.taskservice.Service;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Domain.Enum.Provider;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Dto.TaskResponseDto;
import com.example.taskservice.Dto.TaskUpdateDto;
import com.example.taskservice.Repository.CategoryRepository;
import com.example.taskservice.Repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<TaskResponseDto> getTask(double lat, double lng, Double distanceRange, String providerType, Long categoryId){
        List<Task> tasks= taskRepository.findTasksByFilters(providerType,categoryId,lat,lng,distanceRange);
        List<TaskResponseDto> taskResponseDto = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponseDto dto = new TaskResponseDto();
            BeanUtils.copyProperties(task, dto);
            taskResponseDto.add(dto);
        }
        return taskResponseDto;
    }

    public TaskResponseDto getById(Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            TaskResponseDto taskResponseDto = new TaskResponseDto();
            BeanUtils.copyProperties(task.get(), taskResponseDto);
            return taskResponseDto;
        }else {
            throw new Exception("Task not found");
        }
    }

    public List<Task> getByUserId(Long id) throws Exception{
        return taskRepository.findByUserId(id);
    }

    public Task save(TaskDto taskDto) throws Exception{
        Optional<Category> categoryOptional = categoryRepository.findById(taskDto.getCategoryId());
        if(categoryOptional.isPresent()){
            Task taskEntity = new Task();
            BeanUtils.copyProperties(taskDto, taskEntity);
            if(taskDto.getProvider().equals(Provider.COMPANY.name())){
                taskEntity.setProviderType(Provider.COMPANY);
            }else{
                taskEntity.setProviderType(Provider.FREELANCER);
            }
            taskEntity.setCategory(categoryOptional.get());
            return taskRepository.save(taskEntity);
        }else {
            throw new Exception("Category not found");
        }
    }

    public Task update(TaskUpdateDto taskDto, Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            Optional<Category> categoryOptional = categoryRepository.findById(taskDto.getCategoryId());
            if(categoryOptional.isPresent()){
                Task storedTask = task.get();
                BeanUtils.copyProperties(taskDto, storedTask, "id", "requests");
                storedTask.setCategory(categoryOptional.get());
                taskRepository.save(storedTask);
                return storedTask;
            }else {
                throw new Exception("Category not found");
            }
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
