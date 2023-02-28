package com.teamway.dao;

import com.teamway.dto.QuestionDTO;
import com.teamway.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    QuestionDTO findByUid(String uid);

    void deleteByUid(String uid);
}
