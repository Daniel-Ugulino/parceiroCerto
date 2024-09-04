package com.example.userservice.Dataloader;


import com.example.userservice.Domain.Hirer;
import com.example.userservice.Dto.HirerDto;
import com.example.userservice.Dto.LocationDto;
import com.example.userservice.Repository.HirerRepository;
import com.example.userservice.Service.HirerService;
import com.example.userservice.Service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Order(3)
@Component
public class HirerLoader implements ApplicationRunner {
    @Autowired
    private HirerService hirerService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private HirerRepository hirerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (hirerRepository.count() == 0) {
            String file = "src/main/java/com/example/userservice/Dataloader/Data/hirer.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<Hirer> hirers = objectMapper.readValue(new File(file), objectMapper.getTypeFactory().constructCollectionType(List.class, Hirer.class));

            for (Hirer hirer : hirers) {
                HirerDto hirerDto = new HirerDto();
                BeanUtils.copyProperties(hirer, hirerDto, "location");
                Hirer hirerEntity = hirerService.save(hirerDto);
                if (hirer.getLocation() != null) {
                    LocationDto locationDto = new LocationDto();
                    BeanUtils.copyProperties(hirer.getLocation(), locationDto);
                    locationService.save(locationDto, hirerEntity.getId());
                    System.out.println("O Hirer : " + hirer.getEmail() + " foi incluido com sucesso");
                }
            }
        }
    }
}
