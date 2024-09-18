package com.example.userservice.Service;
import com.example.userservice.Audit.UserContext;
import com.example.userservice.Domain.Company;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.CompanyUpdateDto;
import com.example.userservice.Repository.CompanyRepository;
import com.example.userservice.Utils.ValidateCpfCnpj;
import io.micrometer.observation.annotation.Observed;
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

    @Observed(name = "Company save")
    public Company save(CompanyDto companyDto) throws Exception{
        Company companyEntity = new Company();
        if(ValidateCpfCnpj.validateCNPJ(companyDto.getCnpj())) {
            BeanUtils.copyProperties(companyDto, companyEntity);
            companyEntity.setRole(Roles.COMPANY);
            companyEntity.setPassword(bCryptPasswordEncoder.encode(companyEntity.getPassword()));
            UserContext.setUserId(companyEntity.getEmail());
            companyEntity = companyRepository.save(companyEntity);
            return companyEntity;
        }else {
            throw new Exception("CNPJ not valid");
        }
    }

    public List<Company> listAll(){
        return companyRepository.findAll();
    }

    public Company getById(Long id) throws Exception{
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            return company.get();
        }else {
            throw new Exception("Company not found");
        }
    }

    public Company update(CompanyUpdateDto companyDto, Long id) throws Exception {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company companyEntity = new Company();
            BeanUtils.copyProperties(companyDto, companyEntity,"cnpj","expertise");
            Company storedCompany = optionalCompany.get();
            companyEntity.reset(storedCompany);
            companyEntity.setCnpj(storedCompany.getCnpj());
            companyEntity.setExpertise(storedCompany.getExpertise());
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
