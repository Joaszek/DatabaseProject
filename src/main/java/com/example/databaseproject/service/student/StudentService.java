package com.example.databaseproject.service.student;

import com.example.databaseproject.entities.course.Course;
import com.example.databaseproject.entities.department.Department;
import com.example.databaseproject.entities.student.Student;
import com.example.databaseproject.repository.course.CourseRepository;
import com.example.databaseproject.repository.student.StudentRepository;
import com.example.databaseproject.service.department.DepartmentService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    final StudentRepository studentRepository;
    final CourseRepository courseRepository;
    final DepartmentService departmentService;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, DepartmentService departmentService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.departmentService = departmentService;
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<List<Student>> findByLastName(String lastName) {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> finalList = new ArrayList<>();

        for(Student student :students) {
            if (student.getLastName().equals(lastName)) {
                finalList.add(student);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<List<Student>> findByCoursesContains(String courseName) {

        List<Course> courses = courseRepository.findAll();

        for(Course course : courses) {
            if (course.getName().equals(courseName)) {
                return Optional.of(course.getStudents());
            }
        }

        return Optional.empty();
    }

    public Optional<List<Student>> findByDepartment(String departmentName) {
        Optional<Department> department = departmentService.findByName(departmentName);

        return department.map(Department::getStudents);
    }

    public Optional<List<Student>> findByNumberOfCreditsGreaterThanEqual(int numberOfCreditsGreaterThanEqual) {

        Iterable<Student> allStudents = studentRepository.findAll();
        List<Student> finalStudents = new ArrayList<>();

        for (Student student : allStudents) {
            if (student.getNumberOfCredits() >= numberOfCreditsGreaterThanEqual) {
                finalStudents.add(student);
            }
        }
        return Optional.of(finalStudents);
    }

    public Optional<List<Student>> findByNumberOfCreditsLessThan(int numberOfCreditsLessThan) {

        Iterable<Student> allStudents = studentRepository.findAll();
        List<Student> finalStudents = new ArrayList<>();

        for (Student student : allStudents) {
            if (student.getNumberOfCredits() < numberOfCreditsLessThan) {
                finalStudents.add(student);
            }
        }
        return Optional.of(finalStudents);
    }


    public Optional<Boolean> deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            return Optional.of(Boolean.FALSE);
        }

        try {
            studentRepository.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(Boolean.FALSE);
        } catch (DataAccessException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    public Optional<Student> updateStudent(Long id, Student newStudent) {

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            student.get().setName(newStudent.getName());
            student.get().setDepartment(newStudent.getDepartment());
            student.get().setCourses(newStudent.getCourses());
            student.get().setLastName(newStudent.getLastName());
            student.get().setNumberOfCredits(newStudent.getNumberOfCredits());

            Student savedStudent = studentRepository.save(student.get());

            return Optional.of(savedStudent);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Student> createStudent(Student student) {
        return Optional.of(studentRepository.save(student));
    }
}
