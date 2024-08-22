package com.example.userservice.Client;

import com.example.userservice.Client.ResponseDto.GeolocationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.geoapify.com/v1/geocode/search", name = "gelocationClient")
public interface GeolocationClient {

    @GetMapping()
    GeolocationDto getLocation(
            @RequestParam("text") String text,
            @RequestParam("format") String format,
            @RequestParam("apiKey") String apiKey
    );
}
