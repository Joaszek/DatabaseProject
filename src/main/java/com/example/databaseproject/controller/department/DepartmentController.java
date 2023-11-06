package com.example.databaseproject.controller.department;

import com.example.databaseproject.service.department.DepartmentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
