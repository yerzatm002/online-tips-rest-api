package com.example.onlinetipsrestfullappdemo.repository;

import com.example.onlinetipsrestfullappdemo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {
    Staff findByUsername(String username);
}
