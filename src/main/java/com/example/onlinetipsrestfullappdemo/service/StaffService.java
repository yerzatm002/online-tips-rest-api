package com.example.onlinetipsrestfullappdemo.service;

import com.example.onlinetipsrestfullappdemo.model.Restaurant;
import com.example.onlinetipsrestfullappdemo.model.Role;
import com.example.onlinetipsrestfullappdemo.model.Staff;
import com.example.onlinetipsrestfullappdemo.model.exception.StaffNotFoundException;
import com.example.onlinetipsrestfullappdemo.repository.RoleRepo;
import com.example.onlinetipsrestfullappdemo.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class StaffService implements UserDetailsService {

    private final RestaurantService restaurantService;
    private final StaffRepo staffRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public StaffService(StaffRepo staffRepo, RestaurantService restaurantService, RoleRepo roleRepo){
        this.staffRepo = staffRepo;
        this.restaurantService = restaurantService;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepo.findByUsername(username);
        if (Objects.isNull(staff)){
            throw new UsernameNotFoundException(username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        staff.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority((role.getName()))));
        return new User(staff.getUsername(), staff.getPassword(), authorities);
    }

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public Role deleteRole(Long id){
        Role role = roleRepo.getById(id);
        roleRepo.delete(role);
        return role;
    }

    public Staff addRoleToUser(Long staffId, Long roleId) {
        Staff staff = staffRepo.getById(staffId);
        Role role = roleRepo.getById(roleId);
        staff.getRoles().add(role);
        return staff;
    }

    public Staff removeRoleFromUser(Long staffId, Long roleId){
        Staff staff = staffRepo.getById(staffId);
        Role role = roleRepo.getById(roleId);
        staff.removeRole(role);
        return staff;
    }

   public Staff createStaff(Long restaurantId, Staff staff){
        Staff staffObj = staffRepo.findByUsername(staff.getUsername());
        if(Objects.isNull(staffObj)){
            Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
            staff.setRestaurant(restaurant);
            restaurant.addStaff(staff);
            staff.setPassword(passwordEncoder.encode(staff.getPassword()));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepo.getById(1L));
            staff.setRoles(roles);
            return staffRepo.save(staff);
        } else {
            throw new RuntimeException();
        }
    }

    public Staff getStaff(Long id){
        return staffRepo.findById(id).orElseThrow(()->
                new StaffNotFoundException(id));
    }

    @Transactional
    public Staff updateStaff(Staff staff, Long id){
        Staff updateStaff = getStaff(id);
        updateStaff.setEmail(staff.getEmail());
        updateStaff.setFirstName(staff.getFirstName());
        updateStaff.setImageUrl(staff.getImageUrl());
        updateStaff.setPassword(staff.getPassword());
        updateStaff.setLastName(staff.getLastName());
        updateStaff.setUsername(staff.getUsername());
        return updateStaff;
    }

    public Staff deleteStaff(Long id){
        Staff staff = getStaff(id);
        staffRepo.delete(staff);
        return staff;
    }

    public List<Staff> getAllStaff(){
        return staffRepo.findAll();
    }

    @Transactional
    public Staff uploadImage(MultipartFile file, Long staffId) throws IOException{
        String directory = "D:\\avatar\\";
        Staff staff = getStaff(staffId);
        String filename = staff.getUsername()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        file.transferTo(new File(directory + filename));
        staff.setImageUrl(directory + filename);
        return staff;
    }

}
