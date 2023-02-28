package com.teamway.mapper;

import com.teamway.dto.QuestionDTO;
import com.teamway.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QuestionMapper {
    Question questionDTOToQuestion(QuestionDTO questionDTO);

    QuestionDTO questionToQuestionDTO(Question question);

    List<QuestionDTO> questionListToQuestionDTOList(List<Question> questionList);
}
