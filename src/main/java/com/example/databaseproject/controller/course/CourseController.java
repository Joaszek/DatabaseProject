package com.example.databaseproject.controller.course;

import com.example.databaseproject.entities.course.Course;
import com.example.databaseproject.service.course.CourseService;
import com.example.databaseproject.service.professor.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    final CourseService courseService;
    final ProfessorService professorService;


    public CourseController(CourseService courseService, ProfessorService professorService) {
        this.courseService = courseService;
        this.professorService = professorService;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {

        Optional<Course> course = Optional.empty();

        if (course.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(course.get());
        }

    }

    @GetMapping("/professor/search")
    public ResponseEntity<List<Course>> findByProfessor(@RequestParam  String firstName, @RequestParam String lastName) {

        Optional<List<Course>> courses = Optional.empty();
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(courses.get());
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Course>> findByStudentId(@PathVariable Long studentId) {

        Optional<List<Course>> courses = Optional.empty();
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(courses.get());
        }
    }

    @GetMapping("/info/{dayOfWeek}")
    public ResponseEntity<List<Course>> findByDayOfWeek(@PathVariable String dayOfWeek) {

        Optional<List<Course>> courses = Optional.empty();
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(courses.get());
        }
    }

    @GetMapping("/info/{departmentName}")
    public ResponseEntity<List<Course>> findByDepartment(@PathVariable String departmentName) {

        Optional<List<Course>> courses = Optional.empty();
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(courses.get());
        }
    }

    @GetMapping("/info/{courseName}")
    public ResponseEntity<List<Course>> findByParentCourse(@PathVariable String courseName) {

        Optional<List<Course>> courses = Optional.empty();
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(courses.get());
        }
    }
}
