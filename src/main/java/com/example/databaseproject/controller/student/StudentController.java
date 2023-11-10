package com.example.databaseproject.controller.student;

import com.example.databaseproject.entities.student.Student;
import com.example.databaseproject.service.student.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {

        Optional<Student> student = studentService.findById(id);

        return student.map(student1 -> ResponseEntity.ok().body(student1)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<Student>> findByLastName(@PathVariable String lastName) {

        Optional<List<Student>> students = studentService.findByLastName(lastName);

        return students.map(studentList -> ResponseEntity.ok().body(studentList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{courseName}")
    public ResponseEntity<List<Student>> findByCoursesContains(@PathVariable String courseName) {

        Optional<List<Student>> students = studentService.findByCoursesContains(courseName);

        return students.map(studentList -> ResponseEntity.ok().body(studentList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{departmentName}")
    public ResponseEntity<List<Student>> findByDepartment(@PathVariable String departmentName) {

        Optional<List<Student>> students = studentService.findByDepartment(departmentName);

        return students.map(studentList -> ResponseEntity.ok().body(studentList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{numberOfCreditsGreaterThanEqual}")
    public ResponseEntity<List<Student>> findByNumberOfCreditsGreaterThanEqual(
            @PathVariable Integer numberOfCreditsGreaterThanEqual) {

        Optional<List<Student>> students = studentService.findByNumberOfCreditsGreaterThanEqual(
                numberOfCreditsGreaterThanEqual);

        return students.map(studentList -> ResponseEntity.ok().body(studentList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{numberOfCreditsLessThan}")
    public ResponseEntity<List<Student>> findByNumberOfCreditsLessThan(
            @PathVariable Integer numberOfCreditsLessThan) {

        Optional<List<Student>> students = studentService.findByNumberOfCreditsLessThan(numberOfCreditsLessThan);

        return students.map(studentList -> ResponseEntity.ok().body(studentList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Optional<Boolean> deleted = studentService.deleteById(id);

        if (deleted.get()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {

        Optional<Student> updatedStudent = studentService.updateStudent(id, newStudent);

        return updatedStudent.map(student -> ResponseEntity.ok().body(student)).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Optional<Student> createdStudent = studentService.createStudent(student);

        return createdStudent.map(student1 -> ResponseEntity.ok().body(createdStudent.get())).orElseGet(
                () -> ResponseEntity.notFound().build());
    }
}
