package com.example.onlinetipsrestfullappdemo.service;

import com.example.onlinetipsrestfullappdemo.model.Feedback;
import com.example.onlinetipsrestfullappdemo.model.Staff;
import com.example.onlinetipsrestfullappdemo.model.exception.FeedbackNotFoundException;
import com.example.onlinetipsrestfullappdemo.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FeedbackService {

//    @Autowired
//    private FeedbackRepo feedbackRepo; This one is incorrect because there I did not user dependency injection

    private final FeedbackRepo feedbackRepo;
    private final StaffService staffService;

    @Autowired
    public FeedbackService(FeedbackRepo feedbackRepo, StaffService staffService) {
        this.feedbackRepo = feedbackRepo;
        this.staffService = staffService;
    }

    public Feedback createFeedback(Long staffId, Feedback feedback){
        Staff staff = staffService.getStaff(staffId);
        feedback.setStaff(staff);
        staff.addFeedback(feedback);
        return feedbackRepo.save(feedback);
    }

    public Feedback getFeedback(Long id){
        return feedbackRepo.findById(id).orElseThrow(()->
                new FeedbackNotFoundException(id));//will throw exception if feedback with such id was not found
    }

    @Transactional//edited data to be saved
    public Feedback updateFeedback(Feedback feedback, Long id){
        Feedback updateFeedback = getFeedback(id);
        updateFeedback.setClientName(feedback.getClientName());
        updateFeedback.setDescription(feedback.getDescription());
        updateFeedback.setRating(feedback.getRating());
        return updateFeedback;
    }

    public Feedback deleteFeedback(Long id){
        Feedback feedback = getFeedback(id);
        Staff staff = staffService.getStaff(feedback.getStaff().getId());
        staff.removeFeedback(feedback);
        feedbackRepo.delete(feedback);
        return feedback;
    }

    public List<Feedback> getAllFeedback(){
        return StreamSupport.stream(feedbackRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());//convert Iterable data type to List
    }


}
