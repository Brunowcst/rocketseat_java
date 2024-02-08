package com.rocketseat.project_nwl.modules.certifications.controllers;


import com.rocketseat.project_nwl.modules.certifications.useCases.Top10RankingUseCase;
import com.rocketseat.project_nwl.modules.students.entities.CertificationStudentEntity;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return this.top10RankingUseCase.execute();
    }
}
