package com.rocketseat.project_nwl.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.project_nwl.modules.questions.entities.QuestionEntity;
import com.rocketseat.project_nwl.modules.questions.repository.QuestionsRepository;
import com.rocketseat.project_nwl.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.project_nwl.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.project_nwl.modules.students.entities.AnswersCertificationsEntity;
import com.rocketseat.project_nwl.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.project_nwl.modules.students.entities.StudentEntity;
import com.rocketseat.project_nwl.modules.students.repository.CertificationStudentRepository;
import com.rocketseat.project_nwl.modules.students.repository.StudentRepository;

@Service
public class StudentCertificationAnswerUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIsHasCertificationUseCase verifyIsHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = verifyIsHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception("Usuário já possui certificação!");
        }

        List<QuestionEntity> questionEntity = questionsRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger corretcAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
            var question = questionEntity.stream()
            .filter(q -> q.getId().equals(questionAnswer.getQuestionId()))
            .findFirst().get();

            var findCorrectAlternative = question.getAlternatives().stream().filter(alternative -> alternative.is_correct()).findFirst().get();

            if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId())) {
                questionAnswer.setCorrect(true);
                corretcAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answersCertificationsEntity = AnswersCertificationsEntity.builder()
            .answerId(questionAnswer.getAlternativeId())
            .questionId(questionAnswer.getQuestionId())
            .isCorrect(questionAnswer.isCorrect()).build();

            answersCertifications.add(answersCertificationsEntity);
        });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentRepository.save(studentCreated);
            studentId = studentCreated.getId();
        } else {
            studentId = student.get().getId();
        }


        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
        .technology(dto.getTechnology())
        .grade(corretcAnswers.get())
        .student_id(studentId)
        .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(studentId);
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

        return certificationStudentCreated;

    }
}
