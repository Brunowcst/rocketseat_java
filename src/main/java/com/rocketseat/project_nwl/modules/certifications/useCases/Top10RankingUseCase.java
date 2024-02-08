package com.rocketseat.project_nwl.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.project_nwl.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.project_nwl.modules.students.repository.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute() {
        return this.certificationStudentRepository.findFirst10ByOrderByGradeDesc();
    }
}
