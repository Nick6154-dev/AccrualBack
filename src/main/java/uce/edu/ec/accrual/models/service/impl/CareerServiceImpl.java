package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Career;
import uce.edu.ec.accrual.models.entity.Faculty;
import uce.edu.ec.accrual.models.repository.CareerRepository;
import uce.edu.ec.accrual.models.service.CareerService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CareerServiceImpl implements CareerService {

    @Autowired
    private CareerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idCareer) {
        return repository.findById(idCareer).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Career()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findCareersByFaculty(Faculty faculty) {
        return repository.findCareersByFaculty(faculty).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }
}
