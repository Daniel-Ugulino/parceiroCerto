package com.example.taskservice.Service;
import com.example.taskservice.Domain.Enum.RequestStatus;
import com.example.taskservice.Domain.Request;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.ChatDto;
import com.example.taskservice.Dto.RequestDto;
import com.example.taskservice.Dto.RequestUpdateDto;
import com.example.taskservice.Producer.ChatRequestProducer;
import com.example.taskservice.Repository.RequestRepository;
import com.example.taskservice.Repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ChatRequestProducer chatRequestProducer;


    public Request save(RequestDto requestDto) throws Exception {
        Request requestEntity = new Request();
        Optional<Task> taskOptional = taskRepository.findById(requestDto.getTaskId());
        if(taskOptional.isPresent()){
            BeanUtils.copyProperties(requestDto, requestEntity);
            requestEntity.setUserId(requestDto.getUserId());
            requestEntity.setTask(taskOptional.get());
            requestRepository.save(requestEntity);
            ChatDto cDto = new ChatDto(
                    requestDto.getUserId(),
                    taskOptional.get().getUserId(),
                    requestEntity.getId()
            );
            System.out.println(cDto.getRequestId());
            chatRequestProducer.sendMessage(cDto);
            return requestEntity;
        }else {
            throw new Exception("User not found");
        }
    }

    public Request getById(Long requestId) throws Exception{
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if(requestOptional.isPresent()){
            return requestOptional.get();
        }else {
            throw new Exception("Request not Found");
        }
    }

    public List<Request> getByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    public Request changeStatus(Long requestId, RequestStatus requestStatus) throws Exception{
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if(requestOptional.isPresent()){
            Request requestEntity = requestOptional.get();
            requestEntity.setStatus(requestStatus);
            requestRepository.save(requestEntity);
            return requestEntity;
        }else {
            throw new Exception("Request not Found");
        }
    }

    public Request update(RequestUpdateDto requestDto, Long requestId) throws Exception{
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if(requestOptional.isPresent()){
            Request requestEntity = requestOptional.get();
            BeanUtils.copyProperties(requestDto, requestEntity, "id","taskId","userId","requestStatus","totalPrice");
            requestRepository.save(requestEntity);
            return requestEntity;
        }else {
            throw new Exception("Request not Found");
        }
    }
}
