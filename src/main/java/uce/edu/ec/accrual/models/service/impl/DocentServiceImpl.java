package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.repository.DocentRepository;
import uce.edu.ec.accrual.models.service.DocentService;

import java.util.List;
import java.util.Optional;

@Service
public class DocentServiceImpl implements DocentService {

    @Autowired
    private DocentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of((List<Docent>) repository.findAll()).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idDocent) {
        return repository.findById(idDocent).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> findByIdPerson(Long idPerson) {
        return repository.findByIdPerson(idPerson).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Docent docent) {
        return repository.findByIdPerson(docent.getIdPerson()).map(value ->
                        ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build())
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(docent)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idDocent) {
        return repository.findById(idDocent).map(value -> {
            repository.deleteById(idDocent);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateAll(Docent docent, Long idDocent) {
        return repository.findById(idDocent).map(value ->
                        ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
