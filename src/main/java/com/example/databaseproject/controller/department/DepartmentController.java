package com.example.databaseproject.controller.department;

import com.example.databaseproject.entities.course.Course;
import com.example.databaseproject.entities.department.Department;
import com.example.databaseproject.entities.student.Student;
import com.example.databaseproject.service.department.DepartmentService;
import com.example.databaseproject.service.student.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    final DepartmentService departmentService;
    final StudentService studentService;

    public DepartmentController(DepartmentService departmentService, StudentService studentService) {
        this.departmentService = departmentService;
        this.studentService = studentService;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {

        Optional<Department> department = departmentService.findById(id);
        return department.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/info/{name}")
    public ResponseEntity<Department> findByName(@PathVariable String name) {

        Optional<Department> department = departmentService.findByName(name);
        return department.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAllStudents/{name}")
    public ResponseEntity<List<Student>> findByStudentsContains(@PathVariable String name) {

        Optional<List<Student>> students = departmentService.findByStudentsContains(name);

        return students.map(studentList -> ResponseEntity.ok().body(studentList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Optional<Boolean> deleted = departmentService.deleteById(id);

        if (deleted.get()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,@RequestBody Department department) {

        Optional<Department> updatedDepartment = departmentService.updateDepartment(id, department);

        return updatedDepartment.map(value -> ResponseEntity.ok().body(value)).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {

        Optional<Department> createdDepartment = departmentService.createDepartment(department);

        return createdDepartment.map(department1 -> ResponseEntity.ok().body(createdDepartment.get())).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
