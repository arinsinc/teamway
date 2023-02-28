package com.teamway.service;

import com.teamway.dao.AnswerRepository;
import com.teamway.dao.QuestionRepository;
import com.teamway.dto.AnswerDTO;
import com.teamway.dto.QuestionDTO;
import com.teamway.entity.Answer;
import com.teamway.entity.Personality;
import com.teamway.entity.Question;
import com.teamway.exception.InvalidInputException;
import com.teamway.mapper.AnswerMapper;
import com.teamway.mapper.QuestionMapper;
import com.teamway.service.impl.PersonalityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonalityServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private AnswerMapper answerMapper;

    @InjectMocks
    private PersonalityServiceImpl personalityService;

    @Test
    public void shouldSaveQuestion() {
        // Given
        QuestionDTO questionDTO = new QuestionDTO(UUID.randomUUID().toString(), "Question-1");
        AnswerDTO answer = new AnswerDTO(UUID.randomUUID().toString(), "Answer-1", Personality.INTROVERT);
        List<AnswerDTO> answerList = new ArrayList<>();
        answerList.add(answer);
        questionDTO.setAnswers(answerList);
        Question question = new Question();

        // When
        when(questionMapper.questionDTOToQuestion(questionDTO)).thenReturn(question);
        when(questionRepository.save(question)).thenReturn(question);

        // Then
        personalityService.saveQuestion(questionDTO);
        ArgumentCaptor<Question> questionArgumentCaptor = ArgumentCaptor.forClass(Question.class);
        verify(questionRepository, times(1)).save(questionArgumentCaptor.capture());
        assertEquals(question, questionArgumentCaptor.getValue());
    }

    @Test
    public void shouldSaveQuestionThrowsException() {
        // Given
        QuestionDTO questionDTO = new QuestionDTO(UUID.randomUUID().toString(), "Question-1");
        questionDTO.setAnswers(new ArrayList<AnswerDTO>());
        Question question = questionMapper.questionDTOToQuestion(questionDTO);

        // Then
        assertThrows(InvalidInputException.class, () -> personalityService.saveQuestion(questionDTO));
        verify(questionRepository, times(0)).save(question);

    }

    @Test
    public void shouldUpdateQuestion() {
        // Given
        QuestionDTO questionDTO = new QuestionDTO(UUID.randomUUID().toString(), "Question-1");
        AnswerDTO answer = new AnswerDTO(UUID.randomUUID().toString(), "Answer-1", Personality.INTROVERT);
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();
        answerDTOList.add(answer);
        questionDTO.setAnswers(answerDTOList);
        Question question = new Question();

        // When
        when(questionRepository.findByUid(anyString())).thenReturn(questionDTO);
        when(questionMapper.questionDTOToQuestion(questionDTO)).thenReturn(question);
        when(answerMapper.answerDTOListToAnswerList(answerDTOList)).thenReturn(answerList);
        when(questionRepository.save(question)).thenReturn(question);

        // Then
        personalityService.updateQuestion(questionDTO);
        ArgumentCaptor<Question> questionArgumentCaptor = ArgumentCaptor.forClass(Question.class);
        verify(questionRepository, times(1)).save(questionArgumentCaptor.capture());
        assertEquals(question, questionArgumentCaptor.getValue());
    }

    @Test
    public void shouldDeleteQuestion() {
        // When
        doNothing().when(questionRepository).deleteByUid(anyString());

        // Then
        personalityService.deleteQuestion(UUID.randomUUID().toString());
        verify(questionRepository, times(1)).deleteByUid(anyString());
    }

    @Test
    public void shouldGetQuestions() {
        // Given
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();

        // When
        when(questionRepository.findAll()).thenReturn(questionList);
        when(questionMapper.questionListToQuestionDTOList(questionList)).thenReturn(questionDTOList);

        // Then
        assertEquals( questionDTOList, personalityService.getQuestions());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    public void shouldCheckPersonalityTrait() {
        // Given
        LinkedHashMap<String, String> questionList = new LinkedHashMap<>();
        questionList.put("01", "Answer-1");
        questionList.put("02", "Answer-2");
        AnswerDTO answerDTO = new AnswerDTO("01", "Answer-1", Personality.INTROVERT);

        // When
        when(answerRepository.findByAnswer(anyString())).thenReturn(answerDTO);

        // Then
        assertEquals("You are an introvert", personalityService.checkPersonality(questionList));
        verify(answerRepository, times(2)).findByAnswer(anyString());
    }
}
