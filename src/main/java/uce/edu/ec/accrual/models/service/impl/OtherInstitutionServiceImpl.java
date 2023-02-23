package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.OtherInstitution;
import uce.edu.ec.accrual.models.repository.OtherInstitutionRepository;
import uce.edu.ec.accrual.models.service.OtherInstitutionService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OtherInstitutionServiceImpl implements OtherInstitutionService {

    @Autowired
    private OtherInstitutionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idOtherInstitution) {
        return repository.findById(idOtherInstitution).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new OtherInstitution()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findOtherInstitutionByInstitution(Institution institution) {
        return repository.findOtherInstitutionByInstitution(institution).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new OtherInstitution()));
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(OtherInstitution otherInstitution) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(otherInstitution));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idOtherInstitution) {
        return repository.findById(idOtherInstitution).map(value -> {
            repository.deleteById(idOtherInstitution);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se pudo eliminar"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(OtherInstitution otherInstitution, Long idOtherInstitution) {
        return repository.findById(idOtherInstitution).map(value ->
                        ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(otherInstitution)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new OtherInstitution()));
    }
}
