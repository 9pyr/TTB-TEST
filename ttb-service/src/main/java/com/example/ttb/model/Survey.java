package com.example.ttb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "surveys")
public class Survey {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "VARCHAR(100)")
  private String serviceSatisfaction;

  @Column(nullable = false, columnDefinition = "VARCHAR(100)")
  private String improvementSuggestion;

  @Column(columnDefinition = "NVARCHAR(100)")
  private String feedbackComments;

  public Survey() {
  }

  public Survey(String serviceSatisfaction, String improvementSuggestion, String feedbackComments) {
    this.serviceSatisfaction = serviceSatisfaction;
    this.improvementSuggestion = improvementSuggestion;
    this.feedbackComments = feedbackComments;
  }
}
