package com.testsystem.TestConstructor.repository;

import com.testsystem.TestConstructor.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}