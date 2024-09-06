package com.example.userservice.Dataloader;
import com.example.userservice.Domain.Company;
import com.example.userservice.Dto.CompanyDto;
import com.example.userservice.Dto.LocationDto;
import com.example.userservice.Repository.CompanyRepository;
import com.example.userservice.Service.CompanyService;
import com.example.userservice.Service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Order(1)
@Component
public class CompanyLoader implements ApplicationRunner {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private LocationService locationService;

    @Value("${data.source}")
    private String source;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(companyRepository.count() == 0) {
            String json = source + "company.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<Company> companies = objectMapper.readValue(new File(json), objectMapper.getTypeFactory().constructCollectionType(List.class, Company.class));

            for (Company company : companies) {
                CompanyDto companyDto = new CompanyDto();
                BeanUtils.copyProperties(company, companyDto,"location");
                Company companyEntity = companyService.save(companyDto);
                if (company.getLocation() != null) {
                    LocationDto locationDto = new LocationDto();
                    BeanUtils.copyProperties(company.getLocation(), locationDto);
                    locationService.save(locationDto,companyEntity.getId());
                }
            }
            System.out.println("Companies Saved");
        }
    }
}
