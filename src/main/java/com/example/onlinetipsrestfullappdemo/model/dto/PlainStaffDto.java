package com.example.onlinetipsrestfullappdemo.model.dto;

import com.example.onlinetipsrestfullappdemo.model.Staff;
import lombok.Data;


@Data
public class PlainStaffDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public static PlainStaffDto from(Staff staff){
       PlainStaffDto plainStaffDto = new PlainStaffDto();
       plainStaffDto.setFirstName(staff.getFirstName());
       plainStaffDto.setLastName(staff.getLastName());
       plainStaffDto.setEmail(staff.getEmail());
       plainStaffDto.setUsername(staff.getUsername());
       plainStaffDto.setPassword(staff.getPassword());
       return plainStaffDto;
    }
}
