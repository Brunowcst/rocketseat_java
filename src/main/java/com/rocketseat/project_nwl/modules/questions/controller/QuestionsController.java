package com.rocketseat.project_nwl.modules.questions.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.project_nwl.modules.questions.dto.AlternativeResultDTO;
import com.rocketseat.project_nwl.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.project_nwl.modules.questions.entities.AlternativeEntity;
import com.rocketseat.project_nwl.modules.questions.entities.QuestionEntity;
import com.rocketseat.project_nwl.modules.questions.repository.QuestionsRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsRepository questionsRepository;


    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionsRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
        .collect(Collectors.toList());
        return toMap;
    }


    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
        .id(question.getId())
        .technology(question.getTechnology())
        .description(question.getDescription()).build();

        List<AlternativeResultDTO> alternativeResultDTOs = question.getAlternatives()
        .stream().map(alternative -> mapAlternativeDTO(alternative))
        .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativeResultDTOs);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(AlternativeEntity alternativesResultDTO) {
        return AlternativeResultDTO.builder()
        .id(alternativesResultDTO.getId())
        .description(alternativesResultDTO.getDescription()).build();
    }
    
}
