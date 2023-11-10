package com.example.databaseproject.service.professor;

import com.example.databaseproject.entities.professor.Professor;
import com.example.databaseproject.repository.professor.ProfessorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;

    }

    public Optional<List<Professor>> findAllByTitle(String title) {

        Iterable<Professor> professors = professorRepository.findAll();
        List<Professor> finalList = new ArrayList<>();

        for(Professor professor :professors) {
            if (professor.getTitle().equals(title)) {
                finalList.add(professor);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<List<Professor>> findByFirstName(String firstName) {

        Iterable<Professor> professors = professorRepository.findAll();
        List<Professor> finalList = new ArrayList<>();

        for(Professor professor :professors) {
            if (professor.getFirstName().equals(firstName)) {
                finalList.add(professor);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<List<Professor>> findByLastName(String lastName) {

        Iterable<Professor> professors = professorRepository.findAll();
        List<Professor> finalList = new ArrayList<>();

        for(Professor professor :professors) {
            if (professor.getLastName().equals(lastName)) {
                finalList.add(professor);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<List<Professor>> findByDepartment(String department) {

        Iterable<Professor> professors = professorRepository.findAll();
        List<Professor> finalList = new ArrayList<>();

        for(Professor professor :professors) {
            if (professor.getDepartment().getName().equals(department)) {
                finalList.add(professor);
            }
        }
        return Optional.of(finalList);
    }

    public Optional<Boolean> deleteById(Long id) {
        if (!professorRepository.existsById(id)) {
            return Optional.of(Boolean.FALSE);
        }

        try {
            professorRepository.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (EmptyResultDataAccessException e) {
            return Optional.of(Boolean.FALSE);
        } catch (DataAccessException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    public Optional<Professor> updateProfessor(Long id, Professor oldProfessor) {

        Optional<Professor> professor = professorRepository.findById(id);

        if (professor.isPresent()) {
            professor.get().setCourses(oldProfessor.getCourses());
            professor.get().setDepartment(oldProfessor.getDepartment());
            professor.get().setTitle(oldProfessor.getTitle());
            professor.get().setFirstName(oldProfessor.getFirstName());
            professor.get().setLastName(oldProfessor.getLastName());

            Professor saved = professorRepository.save(professor.get());

            return Optional.of(saved);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Professor> createProfessor(Professor professor) {
        return Optional.of(professorRepository.save(professor));
    }
}
