package com.example.ApiGateway.Dto;

import com.example.ApiGateway.Domain.Roles;
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
    private String accessToken;
}
