package com.example.userservice.Client.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeolocationDto {
    private List<Result> results;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result{
        private double lat;
        private double lon;
    }
}
