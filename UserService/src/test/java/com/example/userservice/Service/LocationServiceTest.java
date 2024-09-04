package com.example.userservice.Service;

import com.example.userservice.Client.GeolocationClient;
import com.example.userservice.Client.ResponseDto.GeolocationDto;
import com.example.userservice.Domain.Hirer;
import com.example.userservice.Domain.Location;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Domain.Users;
import com.example.userservice.Dto.HirerDto;
import com.example.userservice.Dto.HirerUpdateDto;
import com.example.userservice.Dto.LocationDto;
import com.example.userservice.Repository.HirerRepository;
import com.example.userservice.Repository.LocationRepository;
import com.example.userservice.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GeolocationClient geolocationClient;

    private static final String API_KEY = "9e8b2cb5eb0940b1b480063462cb5286";

    private Location location;

    @BeforeEach
    void setUp() {
        this.location = new Location();
        location.setId(1L);
        location.setStreet("123 Main St");
        location.setCity("Springfield");
        location.setState("IL");
        location.setZipCode("62704L");
        location.setCountry("USA");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save")
    void save() throws Exception {
        LocationDto locationDto = new LocationDto();
        BeanUtils.copyProperties(location, locationDto);
        GeolocationDto geolocationDto = new GeolocationDto();
        GeolocationDto.Result result = new GeolocationDto.Result();
        result.setLat(37.7749);
        result.setLon(-122.4194);
        geolocationDto.setResults(List.of(result));

        when(userRepository.findById(1L)).thenReturn(Optional.of(new Users()));
        when(geolocationClient.getLocation(anyString(), eq("json"), eq(API_KEY))).thenReturn(geolocationDto);
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location savedLocation = locationService.save(locationDto, 1L);

        assertNotNull(savedLocation);
        assertEquals(37.7749, savedLocation.getLat());
        assertEquals(-122.4194, savedLocation.getLng());
    }

    @Test
    @DisplayName("List All")
    void listAll() {
        when(locationRepository.findAll()).thenReturn(List.of(location,location));
        List<Location> locationList = locationService.listAll();
        assertEquals(2, locationList.size());
    }

    @Test
    @DisplayName("Get By Id")
    void getById() throws Exception {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
        Location locationGet = locationService.getById(1L);
        assertNotNull(locationGet);
        assertEquals(locationGet.getCity(), location.getCity());
        assertEquals(locationGet.getStreet(), location.getStreet());
        assertEquals(locationGet.getCountry(), location.getCountry());
    }

}