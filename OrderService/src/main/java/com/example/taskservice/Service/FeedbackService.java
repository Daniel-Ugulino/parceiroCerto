package com.example.taskservice.Service;

import com.example.taskservice.Client.ResponseDto.UserDto;
import com.example.taskservice.Client.UserServiceClient;
import com.example.taskservice.Domain.Feedback;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.FeedbackDto;
import com.example.taskservice.Repository.FeedbackRepository;
import com.example.taskservice.Repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private TaskRepository taskRepository;

    public Feedback save(FeedbackDto feedbackDto, Long userId) throws Exception {
        Optional<Task> taskOptional = taskRepository.findById(feedbackDto.getTaskId());
        if(userServiceClient.getUser(feedbackDto.getUserId()).getData() != null && taskOptional.isPresent()) {
            Feedback feedbackEntity = new Feedback();
            BeanUtils.copyProperties(feedbackDto, feedbackEntity);
            feedbackEntity.setUserId(userId);
            feedbackEntity.setTask(taskOptional.get());
            feedbackRepository.save(feedbackEntity);
            return feedbackEntity;
        }else{
            throw new Exception("User is not enabled");
        }
    }

    public List<Feedback> getByTaskId(Long id) throws Exception{
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()){
            return taskOptional.get().getFeedbacks();
        }else{
            throw new Exception("Task not found");
        }
    }

    public Feedback getById(Long id) throws Exception{
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        if(feedbackOptional.isPresent()){
            return feedbackOptional.get();
        }else{
            throw new Exception("Task not found");
        }
    }

    public void delete(Long id) throws Exception{
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        if(feedbackOptional.isPresent()){
            feedbackRepository.deleteById(id);
        }else{
            throw new Exception("Task not found");
        }
    }
}
