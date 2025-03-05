package com.example.ttb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.ttb.model.Choice;
import com.example.ttb.model.Setting;
import com.example.ttb.model.SurveyInput;
import com.example.ttb.repository.SurveyInputRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {
  @Autowired
  private SurveyInputRepository surveyInputRepository;

  // Composite Pattern
  @PostConstruct
  @Transactional
  public void init() {
    if (surveyInputRepository.count() == 0) {
      SurveyInput surveyInput1 = new SurveyInput("rank", "จากการใช้งาน TTB Touch ท่านพึงพอใจระดับใด");
      SurveyInput surveyInput2 = new SurveyInput("radio", "หัวข้อไหนของ TTB Touch ที่ท่านคิดว่าควรปรับปรุงมากที่สุด");
      SurveyInput surveyInput3 = new SurveyInput("text", "คำแนะนำอื่นๆ");

      Setting survey1Setting1 = new Setting("require", "true");
      Setting survey1Setting2 = new Setting("min", "1");
      Setting survey1Setting3 = new Setting("max", "5");
      Setting survey1Setting4 = new Setting("min_title", "1 คือไม่พอใจมาก");
      Setting survey1Setting5 = new Setting("max_title", "5 คือพอใจมาก");

      Setting survey2Setting1 = new Setting("require", "true");

      Choice survey2Choice1 = new Choice("ความเร็วในการเปิด", "1");
      Choice survey2Choice2 = new Choice("การค้นหาเมนูที่ใช้บ่อย", "2");
      Choice survey2Choice3 = new Choice("การถอนเงินโดยไม่ใช้บัตร", "3");

      Setting survey3Setting1 = new Setting("max_length", "100");
      Setting survey3Setting2 = new Setting("require", "false");

      surveyInput1.addSetting(survey1Setting1);
      surveyInput1.addSetting(survey1Setting2);
      surveyInput1.addSetting(survey1Setting3);
      surveyInput1.addSetting(survey1Setting4);
      surveyInput1.addSetting(survey1Setting5);

      surveyInput2.addSetting(survey2Setting1);

      surveyInput2.addChoice(survey2Choice1);
      surveyInput2.addChoice(survey2Choice2);
      surveyInput2.addChoice(survey2Choice3);

      surveyInput3.addSetting(survey3Setting1);
      surveyInput3.addSetting(survey3Setting2);

      surveyInputRepository.saveAll(Arrays.asList(surveyInput1, surveyInput2, surveyInput3));
    }
  }
}
