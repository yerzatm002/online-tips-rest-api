package com.example.onlinetipsrestfullappdemo.repository;

import com.example.onlinetipsrestfullappdemo.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipRepo extends JpaRepository<Tip, Long> {
}
