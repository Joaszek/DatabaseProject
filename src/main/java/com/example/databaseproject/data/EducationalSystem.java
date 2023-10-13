package com.example.databaseproject.data;

import com.example.databaseproject.data.course.Course;
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
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "educationalSystem")
    private List<Course> courses = new ArrayList<>();
}
