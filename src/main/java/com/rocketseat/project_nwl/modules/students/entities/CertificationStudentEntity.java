package com.rocketseat.project_nwl.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {
    private UUID id;
    private UUID student_id;
    private String technology;
    private int grade;
    private List<AnswersCertificationsEntity> answersCertificationsEntities; 
}
