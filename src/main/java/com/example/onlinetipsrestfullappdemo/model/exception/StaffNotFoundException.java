package com.example.onlinetipsrestfullappdemo.model.exception;

import java.text.MessageFormat;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException(Long id){
        super(MessageFormat.format("Could not found staff with id: {0}", id));
    }
}
