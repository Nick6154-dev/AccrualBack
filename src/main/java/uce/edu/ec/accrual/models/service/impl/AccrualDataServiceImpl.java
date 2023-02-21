package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.AccrualData;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.repository.AccrualDataRepository;
import uce.edu.ec.accrual.models.service.AccrualDataService;

import java.util.Optional;

@Service
public class AccrualDataServiceImpl implements AccrualDataService {

    @Autowired
    private AccrualDataRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idAccrualData) {
        return repository.findById(idAccrualData).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findByDocent(Docent docent) {
        return repository.findAccrualDataByDocent(docent).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(AccrualData accrualData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(accrualData));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> {
            repository.deleteById(idAccrualData);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha podido eliminar"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(AccrualData accrualData, Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(accrualData))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
