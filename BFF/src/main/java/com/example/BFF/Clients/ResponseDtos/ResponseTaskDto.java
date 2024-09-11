package com.example.BFF.Clients.ResponseDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseTaskDto {
    private data data;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class data{
            private String title;
            private String description;
            private Long userId;
            private CategoryDto category;
            private Float price;
            private Boolean active;
    }
}
