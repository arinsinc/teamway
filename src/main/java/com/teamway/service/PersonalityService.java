package com.teamway.service;

import com.teamway.dto.QuestionDTO;

import java.util.LinkedHashMap;
import java.util.List;

public interface PersonalityService {
    void saveQuestion(QuestionDTO location);
    void updateQuestion(QuestionDTO location);
    void deleteQuestion(String uid);
    List<QuestionDTO> getQuestions();
    String checkPersonality(LinkedHashMap<String,String> questions);
}
