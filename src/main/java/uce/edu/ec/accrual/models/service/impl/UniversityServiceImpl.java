package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.entity.University;
import uce.edu.ec.accrual.models.repository.UniversityRepository;
import uce.edu.ec.accrual.models.service.UniversityService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository repository;

    @Override
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    public ResponseEntity<?> findById(Long idUniversity) {
        return repository.findById(idUniversity).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new University()));
    }
}
