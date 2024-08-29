package com.example.BFF.Dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private data data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class data{
        private Long id;
        private String name;
        private String password;
        private String email;
        private String phone;
        private String gender;
        private Date birthday;
        private Boolean enabled;
        private LocationDto location;
        private Roles role;
    }
}
