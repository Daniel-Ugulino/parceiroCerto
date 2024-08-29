package com.example.BFF.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTaskDto {
    private data data;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class data{
            private String title;
            private String description;
            private Long userId;
            private String categoryId;
            private Double price;
    }
}
