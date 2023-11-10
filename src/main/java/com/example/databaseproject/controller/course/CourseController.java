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

        Optional<Course> course = courseService.findById(id);

        return course.map(value -> ResponseEntity.ok().body(value)).orElseGet(
                () -> ResponseEntity.notFound().build());

    }

    @GetMapping("/professor/search")
    public ResponseEntity<List<Course>> findByProfessor(@RequestParam  String firstName, @RequestParam String lastName) {

        Optional<List<Course>> courses = courseService.findByProfessor(firstName, lastName);

        return courses.map(courseList -> ResponseEntity.ok().body(courseList)).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Course>> findByStudentId(@PathVariable Long studentId) {

        Optional<List<Course>> courses = courseService.findByStudentId(studentId);
        return courses.map(courseList -> ResponseEntity.ok().body(courseList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/info/{dayOfWeek}")
    public ResponseEntity<List<Course>> findByDayOfWeek(@PathVariable String dayOfWeek) {

        Optional<List<Course>> courses = courseService.findByDayOfWeek(dayOfWeek);
        return courses.map(courseList -> ResponseEntity.ok().body(courseList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/info/{departmentName}")
    public ResponseEntity<List<Course>> findByDepartment(@PathVariable String departmentName) {

        Optional<List<Course>> courses = courseService.findByDepartment(departmentName);
        return courses.map(courseList -> ResponseEntity.ok().body(courseList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/info/{courseName}")
    public ResponseEntity<List<Course>> findByParentCourse(@PathVariable String courseName) {

        Optional<List<Course>> courses = courseService.findByParentCourse(courseName);
        return courses.map(courseList -> ResponseEntity.ok().body(courseList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Optional<Boolean> deleted = courseService.deleteById(id);

        return deleted.map(aBoolean -> ResponseEntity.ok().body(aBoolean)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {

        Optional<Course> updatedCourse = courseService.updateCourse(id, course);

        return updatedCourse.map(value -> ResponseEntity.ok().body(value)).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Optional<Course> createdCourse = courseService.createCourse(course);

        return createdCourse.map(course1 -> ResponseEntity.ok().body(createdCourse.get())).orElseGet(
                () -> ResponseEntity.notFound().build());
    }
}
