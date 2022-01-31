package com.example.onlinetipsrestfullappdemo.model.exception;

import java.text.MessageFormat;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(Long id){
        super(MessageFormat.format("Could not found feedback with id: {0}", id));
    }
}
