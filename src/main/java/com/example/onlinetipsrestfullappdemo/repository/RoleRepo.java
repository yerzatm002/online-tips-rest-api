package com.example.onlinetipsrestfullappdemo.repository;

import com.example.onlinetipsrestfullappdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}
