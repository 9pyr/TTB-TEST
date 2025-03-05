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

import com.example.ttb.model.Choice;
import com.example.ttb.model.Setting;
import com.example.ttb.model.SurveyInput;

@DataJpaTest
class SurveyInputRepositoryTest {

  @Autowired
  private SurveyInputRepository surveyInputRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  void findByType_ExistingType_ReturnsSurveyInput() {
    String type = "rank";
    SurveyInput surveyInput = new SurveyInput(type, "Title1");
    Choice choice1 = new Choice("Choice 1", "value1");
    Setting setting1 = new Setting("Setting 1", "value1");
    surveyInput.addChoice(choice1);
    surveyInput.addSetting(setting1);
    entityManager.persistAndFlush(surveyInput);

    Optional<SurveyInput> foundSurveyInput = surveyInputRepository.findByType(type);

    assertTrue(foundSurveyInput.isPresent());
    assertEquals(type, foundSurveyInput.get().getType());
    assertEquals("Title1", foundSurveyInput.get().getTitle());
    assertEquals(1, foundSurveyInput.get().getChoices().size());
    assertEquals("Choice 1", foundSurveyInput.get().getChoices().get(0).getTitle());
    assertEquals("value1", foundSurveyInput.get().getChoices().get(0).getValue());
    assertEquals(1, foundSurveyInput.get().getSettings().size());
    assertEquals("Setting 1", foundSurveyInput.get().getSettings().get(0).getKey());
    assertEquals("value1", foundSurveyInput.get().getSettings().get(0).getValue());
  }

  @Test
  void findByType_NonExistingType_ReturnsEmptyOptional() {
    String nonExistingType = "NonExistingType";

    Optional<SurveyInput> foundSurveyInput = surveyInputRepository.findByType(nonExistingType);

    assertTrue(foundSurveyInput.isEmpty());
  }

  @Test
  void save_ValidSurveyInput_ReturnsSavedSurveyInputWithId() {
    SurveyInput surveyInput = new SurveyInput("Type1", "Title1");
    Choice choice1 = new Choice("Choice 1", "value1");
    Setting setting1 = new Setting("Setting 1", "value1");
    surveyInput.addChoice(choice1);
    surveyInput.addSetting(setting1);

    SurveyInput savedSurveyInput = surveyInputRepository.save(surveyInput);

    assertTrue(savedSurveyInput.getId() > 0);
    assertEquals("Type1", savedSurveyInput.getType());
    assertEquals("Title1", savedSurveyInput.getTitle());
    assertEquals(1, savedSurveyInput.getChoices().size());
    assertEquals("Choice 1", savedSurveyInput.getChoices().get(0).getTitle());
    assertEquals("value1", savedSurveyInput.getChoices().get(0).getValue());
    assertEquals(1, savedSurveyInput.getSettings().size());
    assertEquals("Setting 1", savedSurveyInput.getSettings().get(0).getKey());
    assertEquals("value1", savedSurveyInput.getSettings().get(0).getValue());
  }

  @Test
  void findAll_ReturnsAllSurveyInputs() {
    SurveyInput surveyInput1 = new SurveyInput("Type1", "Title1");
    SurveyInput surveyInput2 = new SurveyInput("Type2", "Title2");
    entityManager.persist(surveyInput1);
    entityManager.persist(surveyInput2);
    entityManager.flush();

    List<SurveyInput> listSurveyInputs = surveyInputRepository.findAll();

    assertEquals(2, listSurveyInputs.size());
  }

  @Test
  void save_SurveyInputWithMultipleChoicesAndSettings_ReturnsSavedSurveyInput() {
    SurveyInput surveyInput = new SurveyInput("TypeMultiple", "TitleMultiple");
    Choice choice1 = new Choice("Choice 1", "value1");
    Choice choice2 = new Choice("Choice 2", "value2");
    Setting setting1 = new Setting("Setting 1", "value1");
    Setting setting2 = new Setting("Setting 2", "value2");
    surveyInput.addChoice(choice1);
    surveyInput.addChoice(choice2);
    surveyInput.addSetting(setting1);
    surveyInput.addSetting(setting2);

    SurveyInput savedSurveyInput = surveyInputRepository.save(surveyInput);

    assertTrue(savedSurveyInput.getId() > 0);
    assertEquals("TypeMultiple", savedSurveyInput.getType());
    assertEquals("TitleMultiple", savedSurveyInput.getTitle());
    assertEquals(2, savedSurveyInput.getChoices().size());
    assertTrue(savedSurveyInput.getChoices().stream().anyMatch(c -> c.getTitle().equals("Choice 1")));
    assertTrue(savedSurveyInput.getChoices().stream().anyMatch(c -> c.getTitle().equals("Choice 2")));
    assertEquals(2, savedSurveyInput.getSettings().size());
    assertTrue(savedSurveyInput.getSettings().stream().anyMatch(s -> s.getKey().equals("Setting 1")));
    assertTrue(savedSurveyInput.getSettings().stream().anyMatch(s -> s.getKey().equals("Setting 2")));
  }

  @Test
  void delete_ExistingSurveyInput_SurveyInputIsDeleted() {
    SurveyInput surveyInput = new SurveyInput("TypeToDelete", "TitleToDelete");
    Choice choice1 = new Choice("Choice 1", "value1");
    Setting setting1 = new Setting("Setting 1", "value1");
    surveyInput.addChoice(choice1);
    surveyInput.addSetting(setting1);
    SurveyInput savedSurveyInput = entityManager.persistAndFlush(surveyInput);
    Long surveyInputId = savedSurveyInput.getId();

    surveyInputRepository.delete(savedSurveyInput);
    Optional<SurveyInput> deletedSurveyInput = surveyInputRepository.findById(surveyInputId);

    assertFalse(deletedSurveyInput.isPresent());

  }

  @Test
  void update_ExistingSurveyInput_SurveyInputIsUpdated() {
    SurveyInput surveyInput = new SurveyInput("Type1", "Title1");
    Choice choice1 = new Choice("Choice 1", "value1");
    Setting setting1 = new Setting("Setting 1", "value1");
    surveyInput.addChoice(choice1);
    surveyInput.addSetting(setting1);
    SurveyInput savedSurveyInput = entityManager.persistAndFlush(surveyInput);

    savedSurveyInput.setTitle("New Title");
    savedSurveyInput.setType("New Type");
    SurveyInput updatedSurveyInput = surveyInputRepository.save(savedSurveyInput);

    assertEquals("New Title", updatedSurveyInput.getTitle());
    assertEquals("New Type", updatedSurveyInput.getType());
  }
}
