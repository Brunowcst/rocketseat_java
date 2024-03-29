package com.rocketseat.project_nwl.modules.students.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.project_nwl.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.project_nwl.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.project_nwl.modules.students.useCases.StudentCertificationAnswerUseCase;
import com.rocketseat.project_nwl.modules.students.useCases.VerifyIsHasCertificationUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIsHasCertificationUseCase verifyCertificationUseCase;

    @Autowired
    private StudentCertificationAnswerUseCase studentCertificationAnswerUseCase;
    
    @PostMapping("/verifyIsHasCertification")
    public String verifyIsHasCertification(@RequestBody VerifyHasCertificationDTO
    verifyHasCertificationDTO) {

        var result = this.verifyCertificationUseCase.execute(verifyHasCertificationDTO);
        if (result) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        try {
            var result = studentCertificationAnswerUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
