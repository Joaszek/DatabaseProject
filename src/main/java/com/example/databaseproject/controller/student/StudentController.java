package com.example.databaseproject.controller.student;

import com.example.databaseproject.service.student.StudentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
}
