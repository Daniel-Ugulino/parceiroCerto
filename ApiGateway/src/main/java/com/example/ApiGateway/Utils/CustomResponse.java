package com.example.ApiGateway.Utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse<T> {

    private String message;
    private T data;

    public CustomResponse(String message) {
        this.message = message;
    }

}