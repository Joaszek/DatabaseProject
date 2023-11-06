package com.example.databaseproject.controller.professor;

import com.example.databaseproject.service.professor.ProfessorService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProfessorController {

    final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


}
