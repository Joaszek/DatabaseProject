package com.example.databaseproject.controller.professor;

import com.example.databaseproject.entities.professor.Professor;
import com.example.databaseproject.service.professor.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<Professor>> findByTitle(@PathVariable String title) {

        Optional<List<Professor>> professors = professorService.findAllByTitle(title);

        return professors.map(professorList -> ResponseEntity.ok().body(professorList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<List<Professor>> findByFirstName(@PathVariable String firstName) {

        Optional<List<Professor>> professors = professorService.findByFirstName(firstName);

        return professors.map(professorList -> ResponseEntity.ok().body(professorList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<Professor>> findByLastName(@PathVariable String lastName) {

        Optional<List<Professor>> professors = professorService.findByLastName(lastName);

        return professors.map(professorList -> ResponseEntity.ok().body(professorList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{department}")
    public ResponseEntity<List<Professor>> findByDepartment(@PathVariable String department) {

        Optional<List<Professor>> professors = professorService.findByDepartment(department);

        return professors.map(professorList -> ResponseEntity.ok().body(professorList)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Optional<Boolean> deletedProfessor = professorService.deleteById(id);

        if (deletedProfessor.get() == Boolean.TRUE) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {

        Optional<Professor> updatedProfessor = professorService.updateProfessor(id, professor);

        return updatedProfessor.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {

        Optional<Professor> createdProfessor = professorService.createProfessor(professor);

        return createdProfessor.map(professor1 -> ResponseEntity.ok().body(createdProfessor.get())).orElseGet(
                () -> ResponseEntity.notFound().build());
    }
}
