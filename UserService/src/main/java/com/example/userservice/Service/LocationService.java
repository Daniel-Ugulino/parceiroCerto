package com.example.userservice.Service;

import com.example.userservice.Client.GeolocationClient;
import com.example.userservice.Client.ResponseDto.GeolocationDto;
import com.example.userservice.Domain.Location;
import com.example.userservice.Domain.Users;
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

    @Autowired
    private GeolocationClient geolocationClient;

    private static final String API_KEY = "9e8b2cb5eb0940b1b480063462cb5286";

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
            String location = locationEntity.getStreet() + ", " + locationEntity.getCity() + " " + locationEntity.getState() + " " + locationEntity.getZipCode() + ", " + locationEntity.getCountry();
            GeolocationDto geolocationDto = geolocationClient.getLocation(location,"json",API_KEY);
            locationEntity.setLat(geolocationDto.getResults().get(0).getLat());
            locationEntity.setLng(geolocationDto.getResults().get(0).getLon());
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
                Location locationEntity = userEntity.getLocation();
                BeanUtils.copyProperties(locationDto,locationEntity);
                String location = locationEntity.getStreet() + ", " + locationEntity.getCity() + " " + locationEntity.getState() + " " + locationEntity.getZipCode() + ", " + locationEntity.getCountry();
                GeolocationDto geolocationDto = geolocationClient.getLocation(location,"json",API_KEY);
                locationEntity.setLat(geolocationDto.getResults().get(0).getLat());
                locationEntity.setLng(geolocationDto.getResults().get(0).getLon());
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
