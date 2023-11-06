package com.example.databaseproject.service.educationalSystem;

import com.example.databaseproject.repository.educationalSystem.EducationalSystemRepository;
import org.springframework.stereotype.Service;

@Service
public class EducationalSystemService {

    final EducationalSystemRepository educationalSystemRepository;

    public EducationalSystemService(EducationalSystemRepository educationalSystemRepository) {
        this.educationalSystemRepository = educationalSystemRepository;
    }
}
