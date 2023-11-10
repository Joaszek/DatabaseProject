package com.example.databaseproject.service.course;

import com.example.databaseproject.entities.course.Course;
import com.example.databaseproject.entities.professor.Professor;
import com.example.databaseproject.entities.student.Student;
import com.example.databaseproject.repository.course.CourseRepository;
import com.example.databaseproject.repository.professor.ProfessorRepository;
import com.example.databaseproject.repository.student.StudentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    final CourseRepository courseRepository;
    final ProfessorRepository professorRepository;
    final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }


    public Optional<Course> findById(Long id) {

        return courseRepository.findById(id);
    }

    public Optional<List<Course>> findByProfessor(String firstName, String lastName) {

        Iterable<Professor> professorList = professorRepository.findAll();

        for(Professor professor : professorList) {
            if (professor.getFirstName().equals(firstName) && professor.getLastName().equals(lastName)) {
                return Optional.of(professor.getCourses());
            }
        }

        return Optional.empty();
    }

    public Optional<List<Course>> findByStudentId(Long id) {

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            List<Course> courses = student.get().getCourses();

            if (courses.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(courses);
            }
        }

        return Optional.empty();
    }

    public Optional<List<Course>> findByDayOfWeek(String dayOfWeek) {

        int day = DayOfWeek.valueOf(dayOfWeek.toUpperCase()).getValue();
        List<Course> courseList = courseRepository.findAll();
        List<Course> finalList = new ArrayList<>();

        for(Course course : courseList) {
            if (course.getDayOfWeek().getValue() == day) {
                finalList.add(course);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<List<Course>> findByDepartment(String department) {

        List<Course> courseList = courseRepository.findAll();
        List<Course> finalList = new ArrayList<>();

        for(Course course : courseList) {
            if (course.getDepartment().getName().equals(department)) {
                finalList.add(course);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<List<Course>> findByParentCourse(String courseName) {

        List<Course> courses = courseRepository.findAll();

        List<Course> finalList = courses.stream()
                .flatMap(course -> course.getSubCourses().stream())
                .filter(subCourse -> subCourse.getName().equals(courseName))
                .collect(Collectors.toList());

        return Optional.of(finalList);
    }

    public Optional<Boolean> deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            return Optional.of(Boolean.FALSE);
        }

        try {
            courseRepository.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(Boolean.FALSE);
        } catch (DataAccessException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    public Optional<Course> updateCourse(Long id, Course newCourse) {

        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            course.get().setParentCourse(newCourse.getParentCourse());
            course.get().setSubCourses(newCourse.getSubCourses());
            course.get().setClassroom(newCourse.getClassroom());
            course.get().setDepartment(newCourse.getDepartment());
            course.get().setName(newCourse.getName());
            course.get().setDayOfWeek(newCourse.getDayOfWeek());
            course.get().setStudents(newCourse.getStudents());
            course.get().setEducationalSystem(newCourse.getEducationalSystem());
            course.get().setTimeStart(newCourse.getTimeStart());
            course.get().setTimeEnd(newCourse.getTimeEnd());
            course.get().setProfessor(newCourse.getProfessor());

            Course savedCourse = courseRepository.save(course.get());

            return Optional.of(savedCourse);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Course> createCourse(Course course) {
        return Optional.of(courseRepository.save(course));
    }
}
