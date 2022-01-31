package com.example.onlinetipsrestfullappdemo.controller;

import com.example.onlinetipsrestfullappdemo.model.Restaurant;
import com.example.onlinetipsrestfullappdemo.model.dto.PlainRestaurantDto;
import com.example.onlinetipsrestfullappdemo.model.dto.RestaurantDto;
import com.example.onlinetipsrestfullappdemo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {/*TODO: edit controller by adding dto and complete structure*/

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping//works fine
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody PlainRestaurantDto plainRestaurantDto){
        Restaurant restaurant = restaurantService.createRestaurant(Restaurant.from(plainRestaurantDto));
        return new ResponseEntity<>(RestaurantDto.from(restaurant), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")//works fine
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id);
        return new ResponseEntity<>(RestaurantDto.from(restaurant), HttpStatus.OK);
    }

    @PutMapping(value="{id}")//works fine
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long id, @RequestBody PlainRestaurantDto plainRestaurantDto){
        Restaurant restaurant = restaurantService.updateRestaurant(Restaurant.from(plainRestaurantDto), id);
        return new ResponseEntity<>(RestaurantDto.from(restaurant), HttpStatus.OK);
    }

    @GetMapping//works fine
    public ResponseEntity<List<RestaurantDto>> getAll(){
        List<Restaurant> restaurantList =restaurantService.getAllRestaurant();
        List<RestaurantDto> restaurantDtoList = restaurantList.stream().map(RestaurantDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")//works fine
    public ResponseEntity<RestaurantDto> deleteRestaurant(@PathVariable Long id){
        Restaurant restaurant = restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(RestaurantDto.from(restaurant), HttpStatus.OK);
    }

//    @PostMapping(value = "{restaurantId}/staff/{staffId}/add")
//    public ResponseEntity<RestaurantDto> addStaffToRestaurant(@PathVariable Long restaurantId,
//                                                              @PathVariable Long staffId){
//        Restaurant restaurant = restaurantService.addStaffToRestaurant(restaurantId,staffId);
//        return new ResponseEntity<>(RestaurantDto.from(restaurant), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "{restaurantId}/staff/{staffId}/remove")
//    public ResponseEntity<RestaurantDto> removeStaffFromRestaurant(@PathVariable Long restaurantId,
//                                                                   @PathVariable Long staffId){
//        Restaurant restaurant = restaurantService.removeStaffFromRestaurant(restaurantId, staffId);
//        return  new ResponseEntity<>(RestaurantDto.from(restaurant), HttpStatus.OK);
//    }

}
