package com.example.databaseproject.entities.course;

import com.example.databaseproject.entities.professor.Professor;
import com.example.databaseproject.entities.educationalSystem.EducationalSystem;
import com.example.databaseproject.entities.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;


    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id")
    private EducationalSystem educationalSystem;

    @Column
    DayOfWeek dayOfWeek;

    @Column
    LocalTime timeStart;

    @Column
    LocalTime timeEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "parentCourse", cascade = CascadeType.ALL)
    private List<Course> subCourses;

    @ManyToOne
    @JoinColumn(name = "parent_course_id")
    private Course parentCourse;

    @Column
    private String classroom;
}
