package com.teamway.controller;

import com.teamway.dto.QuestionDTO;
import com.teamway.service.PersonalityService;
import com.teamway.utils.ResponseSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * REST API controller for personality trait
 */
@RestController
@RequestMapping("api/v1")
public class PersonalityController {
    private final PersonalityService personalityService;

    @Autowired
    public PersonalityController(PersonalityService personalityService) {
        this.personalityService = personalityService;
    }

    @GetMapping("questions")
    public ResponseEntity<ResponseSerializer> getQuestionsList() {
        List<QuestionDTO> questionList = personalityService.getQuestions();
        ResponseSerializer response = new ResponseSerializer(true, "success", "Questions fetched successfully", questionList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("questions/check-personality")
    public ResponseEntity<ResponseSerializer> checkPersonality(@RequestBody LinkedHashMap<String,String> questions) {
        String result = personalityService.checkPersonality(questions);
        ResponseSerializer response = new ResponseSerializer(true, "success", "Result fetched successfully", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/questions")
    public ResponseEntity<ResponseSerializer> addQuestion(@Valid @RequestBody QuestionDTO question) {
        personalityService.saveQuestion(question);
        ResponseSerializer response = new ResponseSerializer(true, "success", "Question added successfully", null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/questions")
    public ResponseEntity<ResponseSerializer> updateQuestion(@Valid @RequestBody QuestionDTO question) {
        personalityService.updateQuestion(question);
        ResponseSerializer response = new ResponseSerializer(true, "success", "Question updated successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<ResponseSerializer> deleteQuestion(@PathVariable String questionId) {
        personalityService.deleteQuestion(questionId);
        ResponseSerializer response = new ResponseSerializer(true, "success", "Question deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
