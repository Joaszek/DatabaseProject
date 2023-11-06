package com.example.databaseproject.service.course;

import com.example.databaseproject.repository.course.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
