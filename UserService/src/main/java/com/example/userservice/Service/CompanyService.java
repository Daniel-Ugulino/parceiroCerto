package com.example.userservice.Service;
import com.example.userservice.Domain.Company;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.CompanyUpdateDto;
import com.example.userservice.Repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CompanyRepository companyRepository;


    public Company save(CompanyDto companyDto){
        Company companyEntity = new Company();
        BeanUtils.copyProperties(companyDto, companyEntity);
        companyEntity.setRole(Roles.COMPANY);
        companyEntity.setPassword(bCryptPasswordEncoder.encode(companyEntity.getPassword()));
        companyRepository.save(companyEntity);
        return companyEntity;
    }

    public List<Company> listAll(){
        return companyRepository.findAll();
    }

    public Company getById(Long id) throws Exception{
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            return company.get();
        }else {
            throw new Exception("User not found");
        }
    }

    public Company update(CompanyUpdateDto companyDto, Long id) throws Exception {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company companyEntity = new Company();
            BeanUtils.copyProperties(companyDto, companyEntity);
            Company storedCompany = optionalCompany.get();
            companyEntity.reset(storedCompany);
            companyRepository.save(companyEntity);
            return companyEntity;
        } else {
            throw new Exception("User not found");
        }
    }

    public Company addExpertise(List<String> expertises,Long id) throws Exception{
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company storedCompany = optionalCompany.get();
            storedCompany.addExpertise(expertises);
            companyRepository.save(storedCompany);
            return storedCompany;
        }else{
            throw new Exception("User not found");
        }
    }

    public Company removeExpertise(List<String> expertises,Long id) throws Exception{
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company storedCompany = optionalCompany.get();
            storedCompany.removeExpertise(expertises);
            companyRepository.save(storedCompany);
            return storedCompany;
        }else{
            throw new Exception("User not found");
        }
    }
}
