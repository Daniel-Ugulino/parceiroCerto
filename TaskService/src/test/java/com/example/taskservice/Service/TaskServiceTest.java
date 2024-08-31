package com.example.taskservice.Service;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Domain.Enum.Provider;
import com.example.taskservice.Domain.Task;
import com.example.taskservice.Dto.CategoryDto;
import com.example.taskservice.Dto.TaskDto;
import com.example.taskservice.Dto.TaskUpdateDto;
import com.example.taskservice.Repository.CategoryRepository;
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

class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.task = new Task();
        this.task.setId(1L);
        this.task.setTitle("mecanico");
        this.task.setProviderType(Provider.COMPANY);
        this.task.setActive(true);
        this.task.setCategory(new Category(1l,"mecanico"));
        this.task.setDescription("Mecanico Itaguai");
        this.task.setUserId(1L);
        this.task.setPrice(100.00);
    }

    @Test
    void getById() throws Exception {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Task taskGet = taskService.getById(1L);
        assertNotNull(taskGet);
        assertEquals(taskGet.getId(), task.getId());
        assertEquals(taskGet.getCategory(), task.getCategory());
        assertEquals(taskGet.getPrice(), task.getPrice());
    }

    @Test
    void getByUserId() throws Exception {
        when(taskRepository.findByUserId(1L)).thenReturn((List.of(task,task)));
        List<Task> taskList = taskService.getByUserId(1L);
        assertNotNull(taskList);
        assertEquals(2, taskList.size());
    }

    @Test
    void save() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto, "id");
        Task taskSave = taskRepository.save(task);
        assertNotNull(taskSave);
        assertEquals(taskSave.getTitle(), task.getTitle());
        assertEquals(taskSave.getCategory(), task.getCategory());
        assertEquals(taskSave.getDescription(), task.getDescription());
        assertEquals(taskSave.getPrice(), task.getPrice());
    }

    @Test
    void update() throws Exception {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        TaskUpdateDto taskUpdateDto = new TaskUpdateDto();
        BeanUtils.copyProperties(task, taskUpdateDto, "id");
        taskUpdateDto.setPrice(200.00);
        taskUpdateDto.setDescription("Test test");

        Task task = taskService.update(taskUpdateDto,1L);
        assertNotNull(task);
        assertEquals(200.00, task.getPrice());
        assertEquals("Test test", task.getDescription());
    }

    @Test
    void deactivate() throws Exception {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        taskService.deactivate(1L);
        assertNotNull(task);
        assertEquals(false, task.getActive());
    }

    @Test
    void activate() throws Exception {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        taskService.activate(1L);
        assertNotNull(task);
        assertEquals(true, task.getActive());
    }
}