package com.example.onlinetipsrestfullappdemo.model;

import com.example.onlinetipsrestfullappdemo.model.dto.FeedbackDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;

    private String description;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public static Feedback from(FeedbackDto feedbackDto){
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDto.getId());
        feedback.setClientName(feedbackDto.getClientName());
        feedback.setDescription(feedbackDto.getDescription());
        feedback.setRating(feedbackDto.getRating());
        return feedback;
    }
}
