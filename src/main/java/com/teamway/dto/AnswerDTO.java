package com.teamway.dto;

import com.teamway.entity.Personality;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerDTO implements Serializable {
    private final String uid;
    private final String answer;
    private final Personality personality;
}
