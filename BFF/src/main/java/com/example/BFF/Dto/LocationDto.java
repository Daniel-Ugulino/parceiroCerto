package com.example.BFF.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto extends UserDto{
    private String street;
    private String city;
    private String state;
    private Long zipCode;
    private String country;
    private Double lat;
    private Double lng;
}
