package com.example.userservice.Service;

import com.example.userservice.Domain.Hirer;
import com.example.userservice.Domain.Location;
import com.example.userservice.Domain.Users;
import com.example.userservice.Dto.HirerUpdateDto;
import com.example.userservice.Dto.LocationDto;
import com.example.userservice.Repository.LocationRepository;
import com.example.userservice.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Location> listAll(){
        return locationRepository.findAll();
    }

    public Location getById(Long id) throws Exception{
        Optional<Location> location = locationRepository.findById(id);
        if(location.isPresent()){
            return location.get();
        }else {
            throw new Exception("User not found");
        }
    }

    public Location save(LocationDto locationDto, Long id) throws Exception{
        Optional<Users> usersOptional = userRepository.findById(id);
        if(usersOptional.isPresent()){
            Users userEntity = usersOptional.get();
            Location locationEntity = new Location();
            BeanUtils.copyProperties(locationDto, locationEntity);
            userEntity.setLocation(locationEntity);
            userRepository.save(userEntity);
            locationRepository.save(locationEntity);
            return locationEntity;
        } else {
            throw new Exception("User not found");
        }
    }

    public Location update(LocationDto locationDto, Long id) throws Exception {
        Optional<Users> usersOptional = userRepository.findById(id);
        if (usersOptional.isPresent()) {
            Users userEntity = usersOptional.get();
            if(userEntity.getLocation() != null){
                Location locationEntity = new Location();
                BeanUtils.copyProperties(locationDto,locationEntity);
                locationRepository.save(locationEntity);
                return locationEntity;
            }else {
                throw new Exception("Location not found");
            }
        } else {
            throw new Exception("User not found");
        }
    }
}
