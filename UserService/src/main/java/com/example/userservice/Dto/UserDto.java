package com.example.userservice.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String gender;

    @NotNull
    private Date birthday;
}
