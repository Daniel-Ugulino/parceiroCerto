package com.example.userservice.Service;

import com.example.userservice.Domain.Company;
import com.example.userservice.Domain.Company;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.CompanyUpdateDto;
import com.example.userservice.Repository.CompanyRepository;
import com.example.userservice.Repository.CompanyRepository;
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
import static org.mockito.Mockito.times;

class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Company company;

    @BeforeEach
    void setUp() {
        this.company = new Company();
        company.setId(1L);
        company.setName("Test");
        company.setPassword("testPassword");
        company.setEmail("test@example.com");
        company.setPhone("123-456-7890");
        company.setRole(Roles.COMPANY);
        company.setGender("Homem");
        company.setBirthday(new Date(1985, Calendar.MARCH, 20));
        company.setCreatedAt(new Date());
        company.setExpertise(new ArrayList<>(List.of("Java", "Spring Boot", "Microservices", "React", "Docker")));
        company.setCnpj("04.451.162/0001-05");
        company.setProfessionalField("TI");
        company.setDescription("TI Company");
        company.setSocialName("TI Company");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save")
    void save() throws Exception {
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto, "id","role");
        Company saveCompany = companyService.save(companyDto);
        assertNotNull(company);
        assertEquals(saveCompany.getRole(), company.getRole());
        assertEquals(saveCompany.getEmail(), company.getEmail());
        assertEquals(saveCompany.getCnpj(), company.getCnpj());
    }

    @Test
    @DisplayName("List All")
    void listAll() {
        when(companyRepository.findAll()).thenReturn(List.of(company,company));
        List<Company> companyList = companyService.listAll();
        assertEquals(2, companyList.size());
    }

    @Test
    @DisplayName("Get By Id")
    void getById() throws Exception {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        Company companyGet = companyService.getById(1L);
        assertNotNull(companyGet);
        assertEquals(companyGet.getRole(), company.getRole());
        assertEquals(companyGet.getEmail(), company.getEmail());
        assertEquals(companyGet.getCnpj(), company.getCnpj());
    }

    @Test
    @DisplayName("Update")
    void update() throws Exception {
        // Arrange
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        CompanyUpdateDto updatedCompanyDto = new CompanyUpdateDto();
        updatedCompanyDto.setName("Updated Test");
        updatedCompanyDto.setPhone("21312321313122");
        Company updatedCompany = companyService.update(updatedCompanyDto,1L);
        assertNotNull(updatedCompany);
        assertEquals("Updated Test", updatedCompany.getName());
        assertEquals("21312321313122", updatedCompany.getPhone());
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    @DisplayName("Add Expertise")
    void addExpertise() throws Exception {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        List<String> newExpertise = List.of("Kubernetes", "AWS");
        companyService.addExpertise(newExpertise,1L);

        assertTrue(company.getExpertise().containsAll(newExpertise));
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    @DisplayName("Remove Expertise")
    void removeExpertise() throws Exception {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        List<String> expertiseToRemove = List.of("Java", "Spring Boot");
        companyService.removeExpertise(expertiseToRemove,1L);
        assertFalse(company.getExpertise().containsAll(expertiseToRemove));
        verify(companyRepository, times(1)).save(company);
    }
}