package com.example.databaseproject.service.professor;

import com.example.databaseproject.repository.professor.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
}
