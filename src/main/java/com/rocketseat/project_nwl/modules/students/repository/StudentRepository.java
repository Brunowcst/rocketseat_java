package com.rocketseat.project_nwl.modules.students.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.project_nwl.modules.students.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    public Optional<StudentEntity> findByEmail(String email);
}
