package com.example.onlinetipsrestfullappdemo.service;

import com.example.onlinetipsrestfullappdemo.model.Restaurant;
import com.example.onlinetipsrestfullappdemo.model.Staff;
import com.example.onlinetipsrestfullappdemo.model.exception.FeedbackNotFoundException;
import com.example.onlinetipsrestfullappdemo.model.exception.RestaurantNotFoundException;
import com.example.onlinetipsrestfullappdemo.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepo.save(restaurant);
    }

    public Restaurant getRestaurant(Long id){
        return restaurantRepo.findById(id).orElseThrow(()->
                new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant updateRestaurant(Restaurant restaurant, Long id){
        Restaurant updateRestaurant = getRestaurant(id);
        updateRestaurant.setName(restaurant.getName());
        updateRestaurant.setAddress(restaurant.getAddress());
        updateRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        return updateRestaurant;
    }

    public Restaurant deleteRestaurant(Long id){
        Restaurant restaurant = getRestaurant(id);
        restaurantRepo.delete(restaurant);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurant(){
        return restaurantRepo.findAll();
    }

//    @Transactional
//    public Restaurant addStaffToRestaurant(Long restaurantId, Long staffId){
//        Restaurant restaurant = getRestaurant(restaurantId);
//        Staff staff = staffService.getStaff(staffId);
//        if(Objects.nonNull(staff.getRestaurant())){
//            throw new RuntimeException();
//        }
//        restaurant.addStaff(staff);
//        staff.setRestaurant(restaurant);
//        return restaurant;
//    }
//
//    @Transactional
//    public Restaurant removeStaffFromRestaurant(Long restaurantId, Long staffId){
//        Restaurant restaurant = getRestaurant(restaurantId);
//        Staff staff = staffService.getStaff(staffId);
//        restaurant.removeStaff(staff);
//        staff.setRestaurant(null);
//        return restaurant;
//    }


}
