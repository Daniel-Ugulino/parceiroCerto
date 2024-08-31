package com.example.userservice.Service;

import com.example.userservice.Domain.Company;
import com.example.userservice.Domain.Hirer;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.CompanyUpdateDto;
import com.example.userservice.Dto.HirerDto;
import com.example.userservice.Dto.HirerUpdateDto;
import com.example.userservice.Repository.CompanyRepository;
import com.example.userservice.Repository.HirerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HirerServiceTest {

    @InjectMocks
    private HirerService hirerService;

    @Mock
    private HirerRepository hirerRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Hirer hirer;

    @BeforeEach
    void setUp() {
        this.hirer = new Hirer();
        hirer.setId(1L);
        hirer.setName("Test");
        hirer.setPassword("testPassword");
        hirer.setEmail("test@example.com");
        hirer.setPhone("123-456-7890");
        hirer.setRole(Roles.HIRER);
        hirer.setGender("Homem");
        hirer.setBirthday(new Date(1985, Calendar.MARCH, 20));
        hirer.setCreatedAt(new Date());
        hirer.setCpf("788.565.460-51");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save")
    void save() throws Exception {
        when(hirerRepository.save(any(Hirer.class))).thenReturn(hirer);
        HirerDto hirerDto = new HirerDto();
        BeanUtils.copyProperties(hirer, hirerDto, "id","role");
        Hirer saveHirer = hirerService.save(hirerDto);
        assertNotNull(saveHirer);
        assertEquals(saveHirer.getRole(), hirer.getRole());
        assertEquals(saveHirer.getEmail(), hirer.getEmail());
        assertEquals(saveHirer.getCpf(), hirer.getCpf());
    }

    @Test
    @DisplayName("List All")
    void listAll() {
        when(hirerRepository.findAll()).thenReturn(List.of(hirer,hirer));
        List<Hirer> hirerList = hirerService.listAll();
        assertEquals(2, hirerList.size());
    }

    @Test
    @DisplayName("Get By Id")
    void getById() throws Exception {
        when(hirerRepository.findById(1L)).thenReturn(Optional.of(hirer));
        Hirer hirerGet = hirerService.getById(1L);
        assertNotNull(hirerGet);
        assertEquals(hirerGet.getRole(), hirer.getRole());
        assertEquals(hirerGet.getEmail(), hirer.getEmail());
        assertEquals(hirerGet.getCpf(), hirer.getCpf());
    }

    @Test
    @DisplayName("Update")
    void update() throws Exception {
        // Arrange
        when(hirerRepository.findById(1L)).thenReturn(Optional.of(hirer));
        when(hirerRepository.save(any(Hirer.class))).thenReturn(hirer);
        HirerUpdateDto updatedHirerDto = new HirerUpdateDto();
        updatedHirerDto.setName("Updated Test");
        updatedHirerDto.setPhone("21312321313122");
        Hirer updatedHirer = hirerService.update(updatedHirerDto,1L);
        assertNotNull(updatedHirer);
        assertEquals("Updated Test", updatedHirer.getName());
        assertEquals("21312321313122", updatedHirer.getPhone());
        verify(hirerRepository, times(1)).save(any(Hirer.class));
    }
}