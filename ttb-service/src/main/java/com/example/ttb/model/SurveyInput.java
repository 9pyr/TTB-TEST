package com.example.ttb.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "survey_inputs")
public class SurveyInput {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "input_type")
  private String type;

  @Column(columnDefinition = "NVARCHAR(255)")
  private String title;

  // orphanRemoval เพื่อให้ Hibernate ลบ child entity
  // ออกจากฐานข้อมูลอัตโนมัติเมื่อ parent ไม่อ้างอิงถึง child นั้นอีกต่อไป
  @OneToMany(mappedBy = "surveyInput", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Choice> choices = new ArrayList<>();

  @OneToMany(mappedBy = "surveyInput", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Setting> settings = new ArrayList<>();

  public SurveyInput(String type, String title) {
    this.type = type;
    this.title = title;
  }

  public void addSetting(Setting setting) {
    setting.setSurveyInput(this);
    this.settings.add(setting);
  }

  public void addChoice(Choice choice) {
    choice.setSurveyInput(this);
    this.choices.add(choice);
  }
}
