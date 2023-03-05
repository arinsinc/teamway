package com.teamway.utils;

import com.teamway.controller.PersonalityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class InitQuestions implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private PersonalityController personalityController;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String url = "http://localhost:8080/api/v1/questions";
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        for(int i=1,j=1; i<=5; i++,j=j+2) {
            List<LinkedHashMap> answerList = new ArrayList<>();
            LinkedHashMap<String, Object> answer1 = new LinkedHashMap<>();
            LinkedHashMap<String, Object> answer2 = new LinkedHashMap<>();
            data.put("question", "Question-".concat(String.valueOf(i)));
            answer1.put("answer", "Answer-".concat(String.valueOf(j)));
            answer1.put("personality", "INTROVERT");
            answer2.put("answer", "Answer-".concat(String.valueOf(j+1)));
            answer2.put("personality", "EXTROVERT");
            answerList.add(answer1);
            answerList.add(answer2);
            data.put("answers", answerList);
            restTemplate.postForObject(url, data, String.class);
        }
    }
}
