package com.example.onlinetipsrestfullappdemo.model.dto;

import com.example.onlinetipsrestfullappdemo.model.Feedback;
import lombok.Data;


@Data
public class FeedbackDto {
    private Long id;
    private String clientName;
    private String description;
    private Double rating;

    public static FeedbackDto from(Feedback feedback){
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setClientName(feedback.getClientName());
        feedbackDto.setDescription(feedback.getDescription());
        feedbackDto.setRating(feedback.getRating());
        return feedbackDto;
    }
}
