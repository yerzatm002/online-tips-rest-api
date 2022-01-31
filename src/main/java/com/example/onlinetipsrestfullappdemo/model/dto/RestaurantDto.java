package com.example.onlinetipsrestfullappdemo.model.dto;

import com.example.onlinetipsrestfullappdemo.model.Restaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<PlainStaffDto> plainStaffDtoList = new ArrayList<>();

    public static RestaurantDto from(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDto.setPlainStaffDtoList(restaurant.getStaffList().stream().map(PlainStaffDto::from).collect(Collectors.toList()));
        return restaurantDto;
    }

}
