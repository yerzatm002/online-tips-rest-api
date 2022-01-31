package com.example.onlinetipsrestfullappdemo;

import com.example.onlinetipsrestfullappdemo.model.Restaurant;
import com.example.onlinetipsrestfullappdemo.model.Role;
import com.example.onlinetipsrestfullappdemo.service.RestaurantService;
import com.example.onlinetipsrestfullappdemo.service.StaffService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class OnlineTipsRestfullAppDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTipsRestfullAppDemoApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run (RestaurantService restaurantService, StaffService staffService){
        return args -> {
            restaurantService.createRestaurant(new Restaurant(null, "Sandyq", "Abay 75", "87475869688", new ArrayList<>()));

            staffService.saveRole(new Role(null, "ROLE_USER"));
            staffService.saveRole(new Role(null, "ROLE_ADMIN"));
        };
    }

}
