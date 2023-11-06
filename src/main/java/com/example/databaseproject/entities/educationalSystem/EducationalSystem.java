package com.example.databaseproject.entities.educationalSystem;

import com.example.databaseproject.entities.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class EducationalSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column()
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "educationalSystem")
    private List<Course> courses = new ArrayList<>();

}
