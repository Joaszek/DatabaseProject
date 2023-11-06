package com.example.databaseproject.repository.educationalSystem;

import com.example.databaseproject.entities.educationalSystem.EducationalSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalSystemRepository extends CrudRepository<EducationalSystem, Long> {
}
