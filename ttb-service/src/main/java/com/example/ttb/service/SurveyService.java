package com.example.ttb.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ttb.model.Survey;
import com.example.ttb.repository.SurveyRepository;

@Service
public class SurveyService {
  private final SurveyRepository surveyRepository;

  public SurveyService(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  public Survey getSurveyById(Long id) {
    return surveyRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found with id: " + id));
  }

  public Survey saveSurvey(Survey survey) {
    return surveyRepository.save(survey);
  }

}
