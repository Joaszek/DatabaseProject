package com.example.databaseproject.repository.professor;

import com.example.databaseproject.entities.professor.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
