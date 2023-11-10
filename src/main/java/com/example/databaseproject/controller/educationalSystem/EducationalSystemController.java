package com.example.databaseproject.controller.educationalSystem;

import com.example.databaseproject.entities.course.Course;
import com.example.databaseproject.entities.educationalSystem.EducationalSystem;
import com.example.databaseproject.service.educationalSystem.EducationalSystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/educationalSystem")
public class EducationalSystemController {

    final EducationalSystemService educationalSystemService;

    public EducationalSystemController(EducationalSystemService educationalSystemService) {
        this.educationalSystemService = educationalSystemService;
    }

    @GetMapping("/allCourses")
    public ResponseEntity<List<Course>> findAllCourses() {

        Optional<List<Course>> courses = educationalSystemService.findAllCourses();

        return courses.map(courseList -> ResponseEntity.ok().body(courseList)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
