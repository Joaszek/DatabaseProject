package com.example.databaseproject.service.student;

import com.example.databaseproject.repository.student.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
