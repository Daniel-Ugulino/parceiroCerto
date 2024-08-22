package com.example.userservice.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class UserUpdateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String gender;
    @NotNull
    private Date birthday;
}
