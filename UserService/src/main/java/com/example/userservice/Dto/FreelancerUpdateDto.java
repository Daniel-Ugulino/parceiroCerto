package com.example.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerUpdateDto extends UserUpdateDto {
    @NotBlank
    private String description;

    @NotNull
    private List<String> expertise;
}
