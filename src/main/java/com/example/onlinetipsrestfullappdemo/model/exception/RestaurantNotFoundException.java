package com.example.onlinetipsrestfullappdemo.model.exception;

import java.text.MessageFormat;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(Long id){
        super(MessageFormat.format("Could not found restaurant with id: {0}", id));
    }
}
