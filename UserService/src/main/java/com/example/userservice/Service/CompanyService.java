package com.example.userservice.Service;


import com.example.userservice.Domain.Company;
import com.example.userservice.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

//    public Company getCompanyById(int id){
//
//    }
//
//    public Company saveCompany(Company company){
//
//    }
//
//    public Company updateCompany(Company company){
//
//    }
//
//    public Company updateCompany(Company company){
//
//    }
}
