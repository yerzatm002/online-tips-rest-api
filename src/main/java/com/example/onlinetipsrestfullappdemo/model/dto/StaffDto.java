package com.example.onlinetipsrestfullappdemo.model.dto;

import com.example.onlinetipsrestfullappdemo.model.Staff;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class StaffDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String imageUrl;
    private Double totalBalance;
    private Double rating;
    private PlainRestaurantDto plainRestaurantDto;
    private List<FeedbackDto> feedbackDtoList = new ArrayList<>();
    private List<TipDto> tipDtoList = new ArrayList<>();
    private List<RoleDto> roleDtoList = new ArrayList<>();


    public static StaffDto from(Staff staff){
        StaffDto staffDto = new StaffDto();
        staffDto.setId(staff.getId());
        staffDto.setFirstName(staff.getFirstName());
        staffDto.setLastName(staff.getLastName());
        staffDto.setEmail(staff.getEmail());
        staffDto.setUsername(staff.getUsername());
        staffDto.setPassword(staff.getPassword());
        staffDto.setImageUrl(staff.getImageUrl());
        if(Objects.nonNull(staff.getRestaurant())){
            staffDto.setPlainRestaurantDto(PlainRestaurantDto.from(staff.getRestaurant()));
        }
        staffDto.setFeedbackDtoList(staff.getFeedbackList().stream().map(FeedbackDto::from).collect(Collectors.toList()));
        staffDto.setTipDtoList(staff.getTipList().stream().map(TipDto::from).collect(Collectors.toList()));
        staffDto.setTotalBalance(staff.getTotalBalance());
        staffDto.setRating(staff.getRating());
        staffDto.setRoleDtoList(staff.getRoles().stream().map(RoleDto::from).collect(Collectors.toList()));
        return staffDto;
    }

}
