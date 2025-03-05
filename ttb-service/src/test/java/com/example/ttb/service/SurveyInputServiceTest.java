package com.example.ttb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.ttb.model.Choice;
import com.example.ttb.model.Setting;
import com.example.ttb.model.SurveyInput;
import com.example.ttb.repository.SurveyInputRepository;

class SurveyInputServiceTest {

  @Mock
  private SurveyInputRepository surveyInputRepository;

  @InjectMocks
  private SurveyInputService surveyInputService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getSurveyInput_ReturnsAllSurveyInputs() {
    SurveyInput surveyInput1 = new SurveyInput("rank", "Title1");
    surveyInput1.setId(1L);
    SurveyInput surveyInput2 = new SurveyInput("radio", "Title2");
    surveyInput2.setId(2L);
    List<SurveyInput> expectedSurveyInputs = Arrays.asList(surveyInput1, surveyInput2);

    when(surveyInputRepository.findAll()).thenReturn(expectedSurveyInputs);

    List<SurveyInput> actualSurveyInputs = surveyInputService.getSurveyInput();

    assertEquals(expectedSurveyInputs, actualSurveyInputs);
    verify(surveyInputRepository, times(1)).findAll();
  }

  @Test
  void getSurveyInputByType_ExistingType_ReturnsSurveyInput() {
    String type = "rank";
    SurveyInput expectedSurveyInput = new SurveyInput(type, "Title1");
    expectedSurveyInput.setId(1L);
    Choice choice1 = new Choice("Choice 1", "value1");
    choice1.setId(1L);
    Setting setting1 = new Setting("Setting 1", "value1");
    setting1.setId(1L);
    expectedSurveyInput.addChoice(choice1);
    expectedSurveyInput.addSetting(setting1);

    when(surveyInputRepository.findByType(type)).thenReturn(Optional.of(expectedSurveyInput));

    SurveyInput actualSurveyInput = surveyInputService.getSurveyInputByType(type);

    assertEquals(expectedSurveyInput, actualSurveyInput);
    assertEquals(expectedSurveyInput.getChoices().size(), actualSurveyInput.getChoices().size());
    assertEquals(expectedSurveyInput.getChoices().get(0).getTitle(), actualSurveyInput.getChoices().get(0).getTitle());
    assertEquals(expectedSurveyInput.getSettings().size(), actualSurveyInput.getSettings().size());
    assertEquals(expectedSurveyInput.getSettings().get(0).getKey(), actualSurveyInput.getSettings().get(0).getKey());
    verify(surveyInputRepository, times(1)).findByType(type);
  }

  @Test
  void getSurveyInputByType_NonExistingType_ThrowsNotFound() {
    String type = "NonExistingType";
    when(surveyInputRepository.findByType(type)).thenReturn(Optional.empty());

    ResponseStatusException exception = assertThrows(ResponseStatusException.class,
        () -> surveyInputService.getSurveyInputByType(type));
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("Survey not found with type: " + type, exception.getReason());
    verify(surveyInputRepository, times(1)).findByType(type);
  }
}
