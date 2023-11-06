package com.example.databaseproject.controller.educationalSystem;

import com.example.databaseproject.service.educationalSystem.EducationalSystemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EducationalSystemController {

    final EducationalSystemService educationalSystemService;

    public EducationalSystemController(EducationalSystemService educationalSystemService) {
        this.educationalSystemService = educationalSystemService;
    }
}
