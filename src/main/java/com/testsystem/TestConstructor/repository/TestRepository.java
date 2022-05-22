package com.testsystem.TestConstructor.repository;

import com.testsystem.TestConstructor.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TestRepository extends JpaRepository<Test, Long> {
    @Modifying
    @Transactional
    @Query(value="delete from user_test where test_id = :testId ", nativeQuery = true )
    int deleteForeignKey(@Param("testId")Long testId);

    @Modifying
    @Transactional
    @Query(value="delete from result where test_id = :testId ", nativeQuery = true )
    int deleteForeignKeyRes(@Param("testId")Long testId);

    @Modifying
    @Transactional
    @Query(value="delete from test_result where test_id = :testId ", nativeQuery = true )
    int deleteForeignKeyTestRes(@Param("testId")Long testId);



}
