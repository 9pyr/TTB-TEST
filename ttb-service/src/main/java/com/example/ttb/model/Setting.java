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
@Table(name = "settings")
public class Setting {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "setting_key", nullable = false, columnDefinition = "NVARCHAR(255)")
  private String key;

  @Column(name = "setting_value", nullable = false, columnDefinition = "NVARCHAR(255)")
  private String value;

  @ManyToOne
  @JoinColumn(name = "survey_inputs_id", nullable = false)
  @JsonBackReference // ใช้เพื่อป้องกัน infinite recursion หรือ circular reference เมื่อทำการ
                     // serialize ข้อมูล JSON
  private SurveyInput surveyInput;

  public Setting(String key, String value) {
    this.key = key;
    this.value = value;
  }
}
