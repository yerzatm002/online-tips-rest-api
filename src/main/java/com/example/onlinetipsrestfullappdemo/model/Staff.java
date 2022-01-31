package com.example.onlinetipsrestfullappdemo.model;

import com.example.onlinetipsrestfullappdemo.model.dto.PlainStaffDto;
import com.example.onlinetipsrestfullappdemo.model.dto.StaffDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String imageUrl;

    private Double totalBalance = 0.0;

    private Double rating = 5.0;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Tip> tipList = new ArrayList<>();

    @Transactional
    public void addFeedback(Feedback feedback){
        feedbackList.add(feedback);
        Double updateRating = (feedback.getRating() + (getRating()*getFeedbackList().size())) / (getFeedbackList().size()+1);
        setRating(updateRating);
    }

    @Transactional
    public void removeFeedback(Feedback feedback){
        Double updateRating = (getRating() * (getFeedbackList().size()+1) - feedback.getRating())/(getFeedbackList().size());
        setRating(updateRating);
        feedbackList.remove(feedback);
    }

    @Transactional
    public void addTip(Tip tip){
        tipList.add(tip);
        setTotalBalance(getTotalBalance() + tip.getSum());
    }

    @Transactional
    public void removeTip(Tip tip){
        setTotalBalance(getTotalBalance() - tip.getSum());
        tipList.remove(tip);
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void removeRole(Role role){
        roles.remove(role);
    }

    public static Staff from (PlainStaffDto plainStaffDto){
        Staff staff = new Staff();
        staff.setFirstName(plainStaffDto.getFirstName());
        staff.setLastName(plainStaffDto.getLastName());
        staff.setEmail(plainStaffDto.getEmail());
        staff.setUsername(plainStaffDto.getUsername());
        staff.setPassword(plainStaffDto.getPassword());
        return staff;
    }

}
