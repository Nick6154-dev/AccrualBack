package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Subtype;
import uce.edu.ec.accrual.models.entity.Type;
import uce.edu.ec.accrual.models.repository.SubtypeRepository;
import uce.edu.ec.accrual.models.service.SubtypeService;

import java.util.List;
import java.util.Optional;

@Service
public class SubTypeServiceImpl implements SubtypeService {

    @Autowired
    private SubtypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<Subtype>> docents = Optional.of((List<Subtype>) repository.findAll());
        return docents.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idSubtype) {
        Optional<Subtype> docent = repository.findById(idSubtype);
        return docent.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findSubtypeByType(Type type) {
        Optional<List<Subtype>> subtypes = repository.findSubtypesByActivityType(type);
        return subtypes.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> save(Subtype description) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> delete(Subtype description) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> deleteById(Long idSubtype) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> update(Subtype description, Long idSubtype) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
