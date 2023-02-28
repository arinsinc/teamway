package com.teamway.dao;

import com.teamway.dto.QuestionDTO;
import com.teamway.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    QuestionDTO findByUid(String uid);

    void deleteByUid(String uid);

    @Query("SELECT q.uid, q.question, q.answers FROM Question q ORDER BY random()")
    List<QuestionDTO> getQuestions(Pageable pageable);
}
