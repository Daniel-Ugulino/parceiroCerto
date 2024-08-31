package com.example.userservice.Service;

import com.example.userservice.Domain.Freelancer;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.FreelancerDto;
import com.example.userservice.Dto.FreelancerUpdateDto;
import com.example.userservice.Repository.FreelancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FreelancerServiceTest {

    @InjectMocks
    private FreelancerService freelancerService;

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Freelancer freelancer;

    @BeforeEach
    void setUp() {
        this.freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setName("Test");
        freelancer.setPassword("testPassword");
        freelancer.setEmail("test@example.com");
        freelancer.setPhone("123-456-7890");
        freelancer.setRole(Roles.FREELANCER);
        freelancer.setGender("Homem");
        freelancer.setBirthday(new Date(1985, Calendar.MARCH, 20));
        freelancer.setCreatedAt(new Date());
        freelancer.setExpertise(new ArrayList<>(List.of("Java", "Spring Boot", "Microservices", "React", "Docker")));
        freelancer.setCpf("788.565.460-51");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save")
    void save() throws Exception {
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);
        FreelancerDto freelancerDto = new FreelancerDto();
        BeanUtils.copyProperties(freelancer, freelancerDto, "id","role");
        Freelancer saveFreelancer = freelancerService.save(freelancerDto);
        assertNotNull(freelancer);
        assertEquals(saveFreelancer.getRole(), freelancer.getRole());
        assertEquals(saveFreelancer.getEmail(), freelancer.getEmail());
        assertEquals(saveFreelancer.getCpf(), freelancer.getCpf());
    }

    @Test
    @DisplayName("List All")
    void listAll() {
        when(freelancerRepository.findAll()).thenReturn(List.of(freelancer,freelancer));
        List<Freelancer> freelancerList = freelancerService.listAll();
        assertEquals(2, freelancerList.size());
    }

    @Test
    @DisplayName("Get By Id")
    void getById() throws Exception {
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        Freelancer freelancerGet = freelancerService.getById(1L);
        assertNotNull(freelancerGet);
        assertEquals(freelancerGet.getRole(), freelancer.getRole());
        assertEquals(freelancerGet.getEmail(), freelancer.getEmail());
        assertEquals(freelancerGet.getCpf(), freelancer.getCpf());
    }

    @Test
    @DisplayName("Update")
    void update() throws Exception {
        // Arrange
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);
        FreelancerUpdateDto updatedFreelancerDto = new FreelancerUpdateDto();
        updatedFreelancerDto.setName("Updated Test");
        updatedFreelancerDto.setPhone("21312321313122");
        Freelancer updatedFreelancer = freelancerService.update(updatedFreelancerDto,1L);
        assertNotNull(updatedFreelancer);
        assertEquals("Updated Test", updatedFreelancer.getName());
        assertEquals("21312321313122", updatedFreelancer.getPhone());
        verify(freelancerRepository, times(1)).save(any(Freelancer.class));
    }

    @Test
    @DisplayName("Add Expertise")
    void addExpertise() throws Exception {
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);

        List<String> newExpertise = List.of("Kubernetes", "AWS");
        freelancerService.addExpertise(newExpertise,1L);

        assertTrue(freelancer.getExpertise().containsAll(newExpertise));
        verify(freelancerRepository, times(1)).save(freelancer);
    }

    @Test
    @DisplayName("Remove Expertise")
    void removeExpertise() throws Exception {
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);
        List<String> expertiseToRemove = List.of("Java", "Spring Boot");
        freelancerService.removeExpertise(expertiseToRemove,1L);
        assertFalse(freelancer.getExpertise().containsAll(expertiseToRemove));
        verify(freelancerRepository, times(1)).save(freelancer);
    }
}