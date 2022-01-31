package com.example.onlinetipsrestfullappdemo.model.dto;

import com.example.onlinetipsrestfullappdemo.model.Restaurant;
import lombok.Data;

@Data
public class PlainRestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;

    public static PlainRestaurantDto from(Restaurant restaurant){
        PlainRestaurantDto plainRestaurantDto = new PlainRestaurantDto();
        plainRestaurantDto.setId(restaurant.getId());
        plainRestaurantDto.setName(restaurant.getName());
        plainRestaurantDto.setAddress(restaurant.getAddress());
        plainRestaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
        return plainRestaurantDto;
    }
}
