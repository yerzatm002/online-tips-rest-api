package com.example.onlinetipsrestfullappdemo.repository;

import com.example.onlinetipsrestfullappdemo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends CrudRepository<Feedback, Long> {
}
