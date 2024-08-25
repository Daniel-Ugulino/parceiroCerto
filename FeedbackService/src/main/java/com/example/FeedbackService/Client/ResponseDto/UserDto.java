package com.example.FeedbackService.Client.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        private String name;
        private String password;
        private String email;
        private String phone;
        private String gender;
        private Date birthday;
        private Boolean enabled;
        private Roles role;
    }
}
