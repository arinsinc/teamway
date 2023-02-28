package com.teamway.service.impl;

import com.teamway.dao.AnswerRepository;
import com.teamway.dao.QuestionRepository;
import com.teamway.dto.QuestionDTO;
import com.teamway.entity.Personality;
import com.teamway.entity.Question;
import com.teamway.mapper.AnswerMapper;
import com.teamway.mapper.QuestionMapper;
import com.teamway.service.PersonalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonalityServiceImpl implements PersonalityService {
    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final QuestionMapper questionMapper;

    private final AnswerMapper answerMapper;

    @Autowired
    public PersonalityServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository, QuestionMapper questionMapper, AnswerMapper answerMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
    }

    /**
     * Save question
     * @param question - QuestionDTO
     */
    @Override
    @Transactional
    public void saveQuestion(QuestionDTO question) {
        questionRepository.save(questionMapper.questionDTOToQuestion(question));
    }

    /**
     * Update question
     * @param question - QuestionDTO
     */
    @Override
    @Transactional
    public void updateQuestion(QuestionDTO question) {
        Question questionResult = questionMapper.questionDTOToQuestion(questionRepository.findByUid(question.getUid()));
        questionResult.setQuestion(question.getQuestion());
        questionResult.setAnswers(answerMapper.answerDTOListToAnswerList(question.getAnswers()));
        questionRepository.save(questionResult);
    }

    /**
     * Delete question by uid
     * @param uid - Question uid
     */
    @Override
    @Transactional
    public void deleteQuestion(String uid) {
        questionRepository.deleteByUid(uid);
    }

    @Override
    public List<QuestionDTO> getQuestions() {
        Pageable pageable = PageRequest.of(0, 5);
        return questionRepository.getQuestions(pageable);
    }

    /**
     * Check personality
     * @param questions - Questions object
     * @return - String
     */
    @Override
    public String checkPersonality(LinkedHashMap<String, String> questions) {
        List<Personality> traitList = new ArrayList<>();
        Stream<String> stream = questions.values().stream();
        stream.forEach((value) -> {
            traitList.add(answerRepository.findByAnswer(value).getPersonality());
        });
        long introvertCount = traitList.stream().filter(Personality.INTROVERT::equals).count();
        long extrovertCount = traitList.stream().filter(Personality.EXTROVERT::equals).count();
        if (introvertCount > extrovertCount) {
            return "You are an introvert";
        }
        return "You are an extrovert";
    }
}
