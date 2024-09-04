package com.example.FeedbackService.Service;

import com.example.FeedbackService.Domain.Feedback;
import com.example.FeedbackService.Dto.FeedbackConsumerDto;
import com.example.FeedbackService.Dto.FeedbackDto;
import com.example.FeedbackService.Repository.FeedbackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;


    public Feedback update(FeedbackDto feedbackDto) throws Exception {
        Optional<Feedback> feedbackOptional = feedbackRepository.findByRequestId(feedbackDto.getRequestId());
        if(feedbackOptional.isPresent()){
            Feedback feedbackEntity = feedbackOptional.get();
            feedbackEntity.setGrade(feedbackDto.getGrade());
            feedbackEntity.setComments(feedbackDto.getComments());
            feedbackRepository.save(feedbackEntity);
            return feedbackEntity;
        }else{
            throw new Exception("Task not found");
        }
    }

    public Feedback getByRequestId(Long id) throws Exception{
        Optional<Feedback> feedbackOptional = feedbackRepository.findByRequestId(id);
        if(feedbackOptional.isPresent()){
            return feedbackOptional.get();
        }else{
            throw new Exception("Task not found");
        }
    }

    public List<Feedback> getByTaskId(Long id) throws Exception{
        Optional<List<Feedback>> feedbackOptional = feedbackRepository.findByTaskId(id);
        if(feedbackOptional.isPresent()){
            return feedbackOptional.get();
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
