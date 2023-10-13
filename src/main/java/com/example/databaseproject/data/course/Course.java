package com.example.databaseproject.data.course;

import com.example.databaseproject.data.EducationalSystem;
import com.example.databaseproject.data.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id")
    private EducationalSystem educationalSystem;

    //add info about the course

}
