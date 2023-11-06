package com.example.databaseproject.service.department;

import com.example.databaseproject.repository.department.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}
