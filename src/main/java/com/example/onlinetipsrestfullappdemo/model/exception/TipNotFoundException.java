package com.example.onlinetipsrestfullappdemo.model.exception;

import java.text.MessageFormat;

public class TipNotFoundException extends RuntimeException{
    public TipNotFoundException(Long id){
        super(MessageFormat.format("Could not found tip with id: {0}", id));
    }
}
