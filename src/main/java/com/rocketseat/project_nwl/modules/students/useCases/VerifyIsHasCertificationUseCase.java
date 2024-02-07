package com.rocketseat.project_nwl.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.project_nwl.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.project_nwl.modules.students.repository.CertificationStudentRepository;

@Service
public class VerifyIsHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyHasCertificationDTO dto) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        
        if (!result.isEmpty()) {
            return true;
        } 
        return false;
    }
}
