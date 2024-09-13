package com.example.FeedbackService.Service;

import com.example.FeedbackService.Domain.Feedback;
import com.example.FeedbackService.Dto.FeedbackDto;
import com.example.FeedbackService.Repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class FeedbackServiceTest {


    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;

    private Feedback feedback;

    @BeforeEach
    void setUp() {
        this.feedback = new Feedback();
        feedback.setId(1L);
        feedback.setHirerId(1L);
        feedback.setRequestId(1L);
        feedback.setTaskId(1L);
        feedback.setComments("Good work");
        feedback.setGrade(10);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByTaskId() throws Exception {
        when(feedbackRepository.findByTaskId(1L)).thenReturn(Optional.of(List.of(feedback,feedback)));
        List<Feedback> feedbackList = feedbackService.getByTaskId(1L);
        assertNotNull(feedbackList);
        assertEquals(2, feedbackList.size());
    }

    @Test
    void getById() throws Exception {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        Feedback feedbackGet = feedbackService.getById(1L);
        assertNotNull(feedbackGet);
        assertEquals(feedbackGet.getId(), feedback.getId());
        assertEquals(feedbackGet.getTaskId(), feedback.getTaskId());
        assertEquals(feedbackGet.getComments(), feedback.getComments());
    }

    @Test
    void delete() throws Exception {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        doNothing().when(feedbackRepository).deleteById(1L);
        feedbackService.delete(1L);
        verify(feedbackRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).deleteById(1L);
    }
}