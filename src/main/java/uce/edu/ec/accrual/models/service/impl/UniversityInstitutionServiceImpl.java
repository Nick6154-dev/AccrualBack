package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.UniversityInstitution;
import uce.edu.ec.accrual.models.repository.UniversityInstitutionRepository;
import uce.edu.ec.accrual.models.service.UniversityInstitutionService;

import java.util.Optional;

@Service
public class UniversityInstitutionServiceImpl implements UniversityInstitutionService {

    @Autowired
    private UniversityInstitutionRepository repository;

    @Override
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> findById(Long idUniversityInstitution) {
        return repository.findById(idUniversityInstitution).map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> findUniversityInstitutionByInstitution(Institution institution) {
        return repository.findUniversityInstitutionByInstitution(institution).map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> save(UniversityInstitution universityInstitution) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(universityInstitution));
    }

    @Override
    public ResponseEntity<?> deleteById(Long idUniversityInstitution) {
        return repository.findById(idUniversityInstitution).map(value -> {
            repository.deleteById(idUniversityInstitution);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @Override
    public ResponseEntity<?> update(UniversityInstitution universityInstitution, Long idUniversityInstitution) {
        return repository.findById(idUniversityInstitution).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(universityInstitution)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
