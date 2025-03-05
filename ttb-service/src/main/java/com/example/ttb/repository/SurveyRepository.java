package com.example.ttb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ttb.model.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
