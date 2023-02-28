package com.teamway.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionDTO implements Serializable {
    private final String uid;
    private final String question;
    private List<AnswerDTO> answers;
}
