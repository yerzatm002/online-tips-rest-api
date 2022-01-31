package com.example.onlinetipsrestfullappdemo.controller;

import com.example.onlinetipsrestfullappdemo.model.Role;
import com.example.onlinetipsrestfullappdemo.model.Staff;
import com.example.onlinetipsrestfullappdemo.model.dto.PlainStaffDto;
import com.example.onlinetipsrestfullappdemo.model.dto.RoleDto;
import com.example.onlinetipsrestfullappdemo.model.dto.StaffDto;
import com.example.onlinetipsrestfullappdemo.service.StaffService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping(value = "/restaurant/{restaurantId}")
    public ResponseEntity<StaffDto> createStaff(@PathVariable Long restaurantId, @RequestBody PlainStaffDto plainStaffDto){
        Staff createdStaff = staffService.createStaff(restaurantId, Staff.from(plainStaffDto));
        return new ResponseEntity<>(StaffDto.from(createdStaff), HttpStatus.OK);
    }

    @PostMapping(value = "/image/{id}")
    public ResponseEntity<StaffDto> uploadImage(@RequestParam("avatar")MultipartFile file,
                                                @PathVariable Long id) throws IOException{
        Staff staff = staffService.uploadImage(file, id);
        return new ResponseEntity<>(StaffDto.from(staff), HttpStatus.OK);
    }

    @ApiOperation("Login.")
    @PostMapping("/login")
    public void fakeLogin(@ApiParam("Username") @RequestParam String username, @ApiParam("Password") @RequestParam String password) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<StaffDto> getStaff(@PathVariable Long id){
        Staff staff = staffService.getStaff(id);
        return new ResponseEntity<>(StaffDto.from(staff), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAll(){
        List<Staff> staffList = staffService.getAllStaff();
        List<StaffDto> staffDtoList = staffList.stream().map(StaffDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(staffDtoList, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<StaffDto> updateStaff(@PathVariable Long id, @RequestBody PlainStaffDto plainStaffDto){
        Staff editedStaff = staffService.updateStaff(Staff.from(plainStaffDto),id);
        return new ResponseEntity<>(StaffDto.from(editedStaff), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StaffDto> deleteStaff(@PathVariable Long id){
        Staff staff = staffService.deleteStaff(id);
        return new ResponseEntity<>(StaffDto.from(staff), HttpStatus.OK);
    }

    @PostMapping(value = "/role")
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto){
        Role role  = staffService.saveRole(Role.from(roleDto));
        return new ResponseEntity<>(RoleDto.from(role), HttpStatus.OK);
    }

    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<RoleDto> deleteRole(@PathVariable Long id){
        Role role = staffService.deleteRole(id);
        return new ResponseEntity<>(RoleDto.from(role), HttpStatus.OK);
    }

    @PostMapping(value = "/{staffId}/role/{roleId}/add")
    public ResponseEntity<StaffDto> addRoleToStaff(@PathVariable Long staffId,
                                                   @PathVariable Long roleId){
        Staff staff = staffService.addRoleToUser(staffId, roleId);
        return new ResponseEntity<>(StaffDto.from(staff), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{staffId}/role/{roleId}")
    public ResponseEntity<StaffDto> removeRoleFromStaff(@PathVariable Long staffId,
                                                        @PathVariable Long roleId){
        Staff staff = staffService.removeRoleFromUser(staffId,roleId);
        return new ResponseEntity<>(StaffDto.from(staff), HttpStatus.OK);
    }


}
