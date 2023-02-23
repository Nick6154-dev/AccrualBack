package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.repository.InstitutionRepository;
import uce.edu.ec.accrual.models.service.InstitutionService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idInstitution) {
        return repository.findById(idInstitution).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Institution()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findInstitutionByActivity(Long idActivity) {
        return repository.findInstitutionByIdActivity(idActivity).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Institution()));
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Institution institution) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(institution));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idInstitution) {
        return repository.findById(idInstitution).map(value -> {
            repository.deleteById(idInstitution);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha encontrado el id especificado"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Institution institution, Long idInstitution) {
        return repository.findById(idInstitution).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(institution)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
