package com.rocketseat.project_nwl.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.rocketseat.project_nwl.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIsHasCertificationUseCase {
    public boolean execute(VerifyHasCertificationDTO dto) {
        if (dto.getEmail().equals("teste@gmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        } 
        return false;
    }
}
