package com.example.databaseproject.repository.department;

import com.example.databaseproject.entities.department.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
