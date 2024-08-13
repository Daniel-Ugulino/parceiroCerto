package com.example.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String gender;
    @NotBlank
    private Date birthday;
}
