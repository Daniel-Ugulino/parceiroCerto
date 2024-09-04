package com.example.userservice.Dataloader;

import com.example.userservice.Domain.Freelancer;
import com.example.userservice.Dto.FreelancerDto;
import com.example.userservice.Dto.LocationDto;
import com.example.userservice.Repository.FreelancerRepository;
import com.example.userservice.Service.FreelancerService;
import com.example.userservice.Service.LocationService;
import com.example.userservice.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.List;

@Order(2)
@Component
public class FreelancerLoader implements ApplicationRunner {
    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private LocationService locationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(freelancerRepository.count() == 0) {

            String file = "src/main/java/com/example/userservice/Dataloader/Data/freelancer.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<Freelancer> freelancers = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, Freelancer.class));

            for (Freelancer freelancer : freelancers) {
                FreelancerDto freelancerDto = new FreelancerDto();
                BeanUtils.copyProperties(freelancer, freelancerDto, "location");
                Freelancer freelancerEntity = freelancerService.save(freelancerDto);
                if (freelancer.getLocation() != null) {
                    LocationDto locationDto = new LocationDto();
                    BeanUtils.copyProperties(freelancer.getLocation(), locationDto);
                    locationService.save(locationDto, freelancerEntity.getId());
                    System.out.println("O Freelancer : " + freelancer.getEmail() + " foi incluido com sucesso");
                }
            }
        }
    }
}
