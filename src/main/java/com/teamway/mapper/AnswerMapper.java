package com.teamway.mapper;

import com.teamway.dto.AnswerDTO;
import com.teamway.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AnswerMapper {
    Answer answerDTOToAnswer(AnswerDTO answerDTO);

    AnswerDTO answerToAnswerDTO(Answer answer);

    List<AnswerDTO> answerListToAnswerDTOList(List<Answer> answerList);

    List<Answer> answerDTOListToAnswerList(List<AnswerDTO> answerList);
}
