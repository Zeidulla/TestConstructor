package com.testsystem.TestConstructor.repository;

import com.testsystem.TestConstructor.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Modifying
    @Transactional
    @Query(value="delete from test_question where question_id = :questId ", nativeQuery = true )
    int deleteForeignKey(@Param("questId")Long questId);

}
