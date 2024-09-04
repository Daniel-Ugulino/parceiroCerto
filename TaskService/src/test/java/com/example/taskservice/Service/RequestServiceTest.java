package com.example.taskservice.Service;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Domain.Enum.Provider;
import com.example.taskservice.Domain.Enum.RequestStatus;
import com.example.taskservice.Domain.Request;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.RequestUpdateDto;
import com.example.taskservice.Dto.TaskUpdateDto;
import com.example.taskservice.Repository.RequestRepository;
import com.example.taskservice.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RequestServiceTest {
    @InjectMocks
    private RequestService requestService;

    @Mock
    private RequestRepository requestRepository;

    private Request request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.request = new Request();
        this.request.setId(1L);
        this.request.setUserId(1L);
        this.request.setAmount(2);
        this.request.setStatus(RequestStatus.CREATED);
        this.request.setDescription("Mecanico Itaguai");
        this.request.setNotes("Fix car");
    }

    @Test
    void save() throws Exception {
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        Request requestGet = requestService.getById(1L);
        assertNotNull(requestGet);
        assertEquals(requestGet.getId(), request.getId());
        assertEquals(requestGet.getAmount(), request.getAmount());
        assertEquals(requestGet.getDescription(), request.getDescription());
    }

    @Test
    void getById() throws Exception {
        when(requestRepository.findById(1L)).thenReturn(Optional.ofNullable(request));
        Request requestSave = requestService.getById(1L);
        assertNotNull(request);
        assertEquals(requestSave.getId(), request.getId());
        assertEquals(requestSave.getAmount(), request.getAmount());
        assertEquals(requestSave.getDescription(), request.getDescription());
    }

    @Test
    void getByUserId() {
        when(requestRepository.findByUserId(1L)).thenReturn((List.of(request,request)));
        List<Request> requestList = requestService.getByUserId(1L);
        assertNotNull(requestList);
        assertEquals(2, requestList.size());
    }

    @Test
    void changeStatus() throws Exception {
        when(requestRepository.save(any(Request.class))).thenReturn(request);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        Request request = requestService.changeStatus(1L,"ACCEPTED");
        assertNotNull(request);
        assertEquals(RequestStatus.ACCEPTED, request.getStatus());
    }

    @Test
    void update() throws Exception {
        when(requestRepository.save(any(Request.class))).thenReturn(request);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
        RequestUpdateDto requestUpdateDto = new RequestUpdateDto();
        BeanUtils.copyProperties(request, requestUpdateDto, "id");
        requestUpdateDto.setAmount(2);
        requestUpdateDto.setDescription("Test Update");
        requestService.update(requestUpdateDto,1L);
        assertNotNull(request);
        assertEquals(2, request.getAmount());
        assertEquals("Test Update", request.getDescription());
    }
}