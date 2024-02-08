package com.rocketseat.project_nwl.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "answers_certification_students")
public class AnswersCertificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationID;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificationStudentEntity certificationStudentEntity;

    @Column(name = "student_id")
    private UUID studentID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "answer_id")
    private UUID answerId;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
