package com.example.AuthService.Dto;

import com.example.AuthService.Domain.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Roles role;
    private String gender;
    private Date birthday;
}
