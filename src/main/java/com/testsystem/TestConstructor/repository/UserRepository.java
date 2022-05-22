package com.testsystem.TestConstructor.repository;

import com.testsystem.TestConstructor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String activationCode);
    User findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value="delete from result where user_id = :userId ", nativeQuery = true )
    int deleteForeignKey(@Param("userId")Long userId);

    @Modifying
    @Transactional
    @Query(value="delete from user_result where user_id = :userId ", nativeQuery = true )
    int deleteForeignKeyUsRes(@Param("userId")Long userId);

    @Modifying
    @Transactional
    @Query(value="delete from user_result where result_id = :resultId", nativeQuery = true )
    int deleteForeignKeyUsRes2(@Param("resultId")Long resultId);
}
