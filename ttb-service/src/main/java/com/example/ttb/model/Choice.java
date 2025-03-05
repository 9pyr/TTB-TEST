package com.example.ttb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "choices")
public class Choice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
  private String title;

  @Column(name = "choice_value", nullable = false, columnDefinition = "NVARCHAR(255)")
  private String value;

  @ManyToOne
  @JoinColumn(name = "survey_inputs_id", nullable = false)
  @JsonBackReference
  private SurveyInput surveyInput;

  public Choice(String title, String value) {
    this.title = title;
    this.value = value;
  }
}
