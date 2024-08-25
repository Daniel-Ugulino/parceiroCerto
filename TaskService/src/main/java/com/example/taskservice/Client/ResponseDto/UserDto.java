package com.example.taskservice.Client.ResponseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

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
        private LocationDto location;
        private Roles role;
    }
}
