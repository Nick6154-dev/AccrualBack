package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Faculty;
import uce.edu.ec.accrual.models.entity.University;
import uce.edu.ec.accrual.models.repository.FacultyRepository;
import uce.edu.ec.accrual.models.service.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of((List<Faculty>)repository.findAll()).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idFaculty) {
        return repository.findById(idFaculty).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Faculty()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findFacultiesByUniversity(University university) {
        return repository.findFacultiesByUniversity(university).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }
}
