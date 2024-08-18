package com.example.userservice.Controller;

import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.CompanyUpdateDto;
import com.example.userservice.Repository.CompanyRepository;
import com.example.userservice.Service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    private CompanyDto companyDto;
    private CompanyUpdateDto companyUpdateDto;
    private List<CompanyDto> companyDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
//        CompanyDto companyDto = new CompanyDto();
//        companyDto.set
    }

    @Test
    @DisplayName("Test Save")
    void add() {
    }

    @Test
    @DisplayName("Test Get All")
    void getAll() {
    }

    @Test
    @DisplayName("Test Get By Id")
    void get() {
    }

    @Test
    @DisplayName("Test Update")
    void update() {
    }

    @Test
    @DisplayName("Test add Expertise")
    void addExpertise() {
    }

    @Test
    @DisplayName("Test remove Expertise")
    void removeExpertise() {
    }
}