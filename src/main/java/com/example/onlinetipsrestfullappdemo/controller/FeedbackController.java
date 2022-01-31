package com.example.onlinetipsrestfullappdemo.controller;

import com.example.onlinetipsrestfullappdemo.model.Feedback;
import com.example.onlinetipsrestfullappdemo.model.dto.FeedbackDto;
import com.example.onlinetipsrestfullappdemo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping(value = "staff/{staffId}")
    public ResponseEntity<FeedbackDto> createFeedback(@PathVariable Long staffId,
                                                      @RequestBody FeedbackDto feedbackDto){
        Feedback createdFeedback = feedbackService.createFeedback(staffId,Feedback.from(feedbackDto));
        return new ResponseEntity<>(FeedbackDto.from(createdFeedback), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FeedbackDto> getFeedback(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedback(id);
        return new ResponseEntity<>(FeedbackDto.from(feedback), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable Long id,
                                                      @RequestBody FeedbackDto feedbackDto){
        Feedback feedback1 = feedbackService.updateFeedback(Feedback.from(feedbackDto),id);
        return new ResponseEntity<>(FeedbackDto.from(feedback1), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAll(){
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        List<FeedbackDto> feedbackDtoList = feedbackList.stream().map(FeedbackDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(feedbackDtoList, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<FeedbackDto> deleteFeedback(@PathVariable Long id){
        Feedback feedback = feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(FeedbackDto.from(feedback), HttpStatus.OK);
    }

}
