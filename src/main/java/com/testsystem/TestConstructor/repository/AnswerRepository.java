package com.testsystem.TestConstructor.repository;

import com.testsystem.TestConstructor.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
