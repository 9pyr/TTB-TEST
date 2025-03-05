package com.example.ttb.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.ttb.model.Survey;

@DataJpaTest
class SurveyRepositoryTest {

  @Autowired
  private SurveyRepository surveyRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  void findById_ExistingId_ReturnsSurvey() {
    Survey survey = new Survey("4", "3", "Great service");
    Survey savedSurvey = entityManager.persistAndFlush(survey);
    Long surveyId = savedSurvey.getId();

    Optional<Survey> foundSurvey = surveyRepository.findById(surveyId);

    assertTrue(foundSurvey.isPresent());
    assertEquals(savedSurvey.getServiceSatisfaction(), foundSurvey.get().getServiceSatisfaction());
    assertEquals(savedSurvey.getImprovementSuggestion(), foundSurvey.get().getImprovementSuggestion());
    assertEquals(savedSurvey.getFeedbackComments(), foundSurvey.get().getFeedbackComments());
  }

  @Test
  void findById_NonExistingId_ReturnsEmptyOptional() {
    Long nonExistingId = 999L;

    Optional<Survey> foundSurvey = surveyRepository.findById(nonExistingId);

    assertTrue(foundSurvey.isEmpty());
  }

  @Test
  void save_ValidSurvey_ReturnsSavedSurveyWithId() {
    Survey survey = new Survey("4", "3", "Great service");

    Survey savedSurvey = surveyRepository.save(survey);

    assertTrue(savedSurvey.getId() > 0);
    assertEquals(survey.getServiceSatisfaction(), savedSurvey.getServiceSatisfaction());
    assertEquals(survey.getImprovementSuggestion(), savedSurvey.getImprovementSuggestion());
    assertEquals(survey.getFeedbackComments(), savedSurvey.getFeedbackComments());
  }

  @Test
  void findAll_ReturnsAllSurveys() {
    Survey survey1 = new Survey("4", "3", "Great service");
    Survey survey2 = new Survey("1", "3", "Great service");
    entityManager.persist(survey1);
    entityManager.persist(survey2);
    entityManager.flush();

    List<Survey> listSurvey = surveyRepository.findAll();

    assertEquals(2, listSurvey.size());
  }

  @Test
  void delete_ExistingSurvey_SurveyIsDeleted() {
    Survey survey = new Survey("4", "3", "Great service");
    Survey savedSurvey = entityManager.persistAndFlush(survey);
    Long surveyId = savedSurvey.getId();

    surveyRepository.delete(savedSurvey);

    Optional<Survey> deletedSurvey = surveyRepository.findById(surveyId);
    assertFalse(deletedSurvey.isPresent());
  }

  @Test
  void update_ExistingSurvey_SurveyIsUpdated() {
    Survey survey = new Survey("4", "3", "Great service");
    Survey savedSurvey = entityManager.persistAndFlush(survey);

    savedSurvey.setServiceSatisfaction("5");
    savedSurvey.setImprovementSuggestion("2");
    savedSurvey.setFeedbackComments("Awesome!");
    Survey updatedSurvey = surveyRepository.save(savedSurvey);

    assertEquals("5", updatedSurvey.getServiceSatisfaction());
    assertEquals("2", updatedSurvey.getImprovementSuggestion());
    assertEquals("Awesome!", updatedSurvey.getFeedbackComments());
  }
}
