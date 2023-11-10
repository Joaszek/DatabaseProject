package com.example.databaseproject.service.department;

import com.example.databaseproject.entities.department.Department;
import com.example.databaseproject.entities.student.Student;
import com.example.databaseproject.repository.department.DepartmentRepository;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findByName(String name) {

        Iterable<Department> departmentList = departmentRepository.findAll();

        for (Department department : departmentList) {
            if (department.getName().equals(name)) {
                return Optional.of(department);
            }
        }

        return Optional.empty();
    }

    public Optional<Department> findById(Long id) {

        return departmentRepository.findById(id);
    }

    public Optional<List<Student>> findByStudentsContains(String name) {

        Optional<Department> department = findByName(name);

        if (department.isPresent()) {
            return Optional.of(department.get().getStudents());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Boolean> deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            return Optional.of(Boolean.FALSE);
        }

        try {
            departmentRepository.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(Boolean.FALSE);
        } catch (DataAccessException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    public Optional<Department> updateDepartment(Long id, Department newDepartment) {

        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            department.get().setCourses(newDepartment.getCourses());
            department.get().setStudents(newDepartment.getStudents());
            department.get().setProfessors(newDepartment.getProfessors());
            department.get().setName(newDepartment.getName());

            Department savedDepartment = departmentRepository.save(department.get());

            return Optional.of(savedDepartment);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Department> createDepartment(Department department) {

        return Optional.of(departmentRepository.save(department));
    }
}
