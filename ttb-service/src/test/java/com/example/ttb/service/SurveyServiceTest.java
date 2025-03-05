package com.example.ttb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.ttb.model.Survey;
import com.example.ttb.repository.SurveyRepository;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {

  @Mock
  private SurveyRepository surveyRepository;

  @InjectMocks
  private SurveyService surveyService;

  private Survey survey;

  @BeforeEach
  void setUp() {
    survey = new Survey("5", "2", "Great service");
    survey.setId(1L);
  }

  @Test
  void getSurveyById_ExistingId_ReturnsSurvey() {
    when(surveyRepository.findById(1L)).thenReturn(Optional.of(survey));

    Survey foundSurvey = surveyService.getSurveyById(1L);

    assertNotNull(foundSurvey);
    assertEquals("5", foundSurvey.getServiceSatisfaction());
    assertEquals("2", foundSurvey.getImprovementSuggestion());
    assertEquals("Great service", foundSurvey.getFeedbackComments());
  }

  @Test
  void getSurveyById_NonExistingId_ThrowsNotFound() {
    when(surveyRepository.findById(999L)).thenReturn(Optional.empty());

    ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
      surveyService.getSurveyById(999L);
    });

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("Survey not found with id: 999", exception.getReason());
  }

  @Test
  void saveSurvey_ValidSurvey_ReturnsSavedSurvey() {
    when(surveyRepository.save(any(Survey.class))).thenReturn(survey);

    Survey savedSurvey = surveyService.saveSurvey(new Survey("5", "2", "Great service"));

    assertNotNull(savedSurvey);
    assertEquals("5", savedSurvey.getServiceSatisfaction());
    assertEquals("2", savedSurvey.getImprovementSuggestion());
    assertEquals("Great service", savedSurvey.getFeedbackComments());
  }

}