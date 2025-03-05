package com.example.ttb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ttb.model.SurveyInput;

@Repository
public interface SurveyInputRepository extends JpaRepository<SurveyInput, Long> {
  Optional<SurveyInput> findByType(String type);
}
