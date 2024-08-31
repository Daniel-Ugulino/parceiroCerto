package com.example.taskservice.Service;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Domain.Enum.Provider;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Dto.TaskUpdateDto;
import com.example.taskservice.Repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

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
        Optional<Category> categoryOptional = categoryRepository.findById(taskDto.getCategoryId());
        if(categoryOptional.isPresent()){
            Task taskEntity = new Task();
            BeanUtils.copyProperties(taskDto, taskEntity);
            System.out.println(taskDto.getProvider());
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
            Task storedTask = task.get();
            BeanUtils.copyProperties(taskDto, storedTask, "id", "requests");
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
