package com.example.ttb.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ttb.model.SurveyInput;
import com.example.ttb.repository.SurveyInputRepository;

@Service
public class SurveyInputService {
  private final SurveyInputRepository surveyInputRepository;

  public SurveyInputService(SurveyInputRepository surveyInputRepository) {
    this.surveyInputRepository = surveyInputRepository;
  }

  public List<SurveyInput> getSurveyInput() {
    return surveyInputRepository.findAll();
  }

  public SurveyInput getSurveyInputByType(String type) {
    return surveyInputRepository.findByType(type)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found with type: " + type));
  }

}
