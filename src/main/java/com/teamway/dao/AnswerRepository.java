package com.teamway.dao;

import com.teamway.dto.AnswerDTO;
import com.teamway.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    AnswerDTO findByUid(String uid);

    AnswerDTO findByAnswer(String answer);
}
