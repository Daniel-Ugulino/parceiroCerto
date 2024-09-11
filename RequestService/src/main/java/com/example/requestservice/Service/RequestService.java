package com.example.requestservice.Service;
import com.example.requestservice.Domain.Enum.RequestStatus;
import com.example.requestservice.Domain.Request;
import com.example.requestservice.Dto.ChatDto;
import com.example.requestservice.Dto.RequestDto;
import com.example.requestservice.Dto.RequestUpdateDto;
import com.example.requestservice.Producer.ChatRequestProducer;
import com.example.requestservice.Dto.FeedbackDto;
import com.example.requestservice.Producer.FeedbackRequestProducer;
import com.example.requestservice.Repository.RequestRepository;
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
    private ChatRequestProducer chatRequestProducer;
    @Autowired
    private FeedbackRequestProducer feedbackRequestProducer;

    public Request save(RequestDto requestDto) throws Exception {
        Request requestEntity = new Request();
        BeanUtils.copyProperties(requestDto, requestEntity);
        requestRepository.save(requestEntity);
        ChatDto cDto = new ChatDto(
                requestDto.getUserId(),
                requestDto.getProviderId(),
                requestEntity.getId()
        );
        chatRequestProducer.sendMessage(cDto);
        return requestEntity;
    }

    public Request getById(Long id) throws Exception{
        Optional<Request> requestOptional = requestRepository.findById(id);
        if(requestOptional.isPresent()){
            return requestOptional.get();
        }else {
            throw new Exception("Request not Found");
        }
    }

    public List<Request> getByTaskId(Long taskId) throws Exception{
        List<Request> requestList = requestRepository.findByTaskId(taskId);
        if (!requestList.isEmpty()) {
            return requestList;
        } else {
            throw new Exception("Request not found");
        }
    }

    public List<Request> getByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    public Request changeStatus(Long requestId, String requestStatus) throws Exception{
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if(requestOptional.isPresent()){
            Request requestEntity = requestOptional.get();
            requestEntity.setStatus(RequestStatus.valueOf(requestStatus));
            if(requestEntity.getStatus() == RequestStatus.DONE){
                FeedbackDto fDto = new FeedbackDto(
                        requestEntity.getUserId(),
                        requestEntity.getTaskId(),
                        requestEntity.getId()
                );
                feedbackRequestProducer.sendMessage(fDto);
            }
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
            if(requestEntity.getStatus() == RequestStatus.ACCEPTED){
                throw new Exception("Cannot update accepted request");
            }
            BeanUtils.copyProperties(requestDto, requestEntity, "id","taskId","userId","requestStatus","totalPrice","amount");
            requestRepository.save(requestEntity);
            return requestEntity;
        }else {
            throw new Exception("Request not Found");
        }
    }
}
