package com.example.onlinetipsrestfullappdemo.model;

import com.example.onlinetipsrestfullappdemo.model.dto.PlainRestaurantDto;
import com.example.onlinetipsrestfullappdemo.model.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class    Restaurant {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Staff> staffList = new ArrayList<>();

    public void addStaff(Staff staff){
        staffList.add(staff);
    }

    public void removeStaff(Staff staff){
        staffList.remove(staff);
    }

    public static Restaurant from(PlainRestaurantDto plainRestaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(plainRestaurantDto.getId());
        restaurant.setName(plainRestaurantDto.getName());
        restaurant.setAddress(plainRestaurantDto.getAddress());
        restaurant.setPhoneNumber(plainRestaurantDto.getPhoneNumber());
        return restaurant;
    }
}
