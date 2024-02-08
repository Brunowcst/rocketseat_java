package com.rocketseat.project_nwl.modules.questions.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.project_nwl.modules.questions.entities.QuestionEntity;

public interface QuestionsRepository extends JpaRepository<QuestionEntity, UUID> {
    
    List<QuestionEntity> findByTechnology(String technology);
}
