package com.example.databaseproject.service.educationalSystem;

import com.example.databaseproject.entities.course.Course;
import com.example.databaseproject.repository.course.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationalSystemService {

    final CourseRepository courseRepository;

    public EducationalSystemService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<List<Course>> findAllCourses() {
        return Optional.of(courseRepository.findAll());
    }

}
