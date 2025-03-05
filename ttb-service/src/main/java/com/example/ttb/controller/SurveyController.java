package com.example.ttb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ttb.model.Survey;
import com.example.ttb.service.SurveyService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(
            @Parameter(description = "ID แบบสอบถามที่เคยมีบันทึกไว้") @PathVariable Long id) {
        return ResponseEntity.ok(surveyService.getSurveyById(id));
    }

    @PostMapping()
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey surveyDto) {
        Survey survey = new Survey(surveyDto.getServiceSatisfaction(), surveyDto.getImprovementSuggestion(),
                surveyDto.getFeedbackComments());
        return ResponseEntity.ok(surveyService.saveSurvey(survey));
    }

}