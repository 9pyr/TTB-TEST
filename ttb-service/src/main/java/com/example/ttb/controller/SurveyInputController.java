package com.example.ttb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttb.model.SurveyInput;
import com.example.ttb.service.SurveyInputService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/survey-inputs")
public class SurveyInputController {
  private final SurveyInputService surveyInputService;

  public SurveyInputController(SurveyInputService surveyInputService) {
    this.surveyInputService = surveyInputService;
  }

  @GetMapping()
  public List<SurveyInput> getSurveyInputs() {
    return surveyInputService.getSurveyInput();
  }

  @GetMapping("/{type}")
  public SurveyInput getSurveyInputByType(
      @Parameter(description = "rank | radio | text") @PathVariable String type) {
    return surveyInputService.getSurveyInputByType(type);
  }

}
