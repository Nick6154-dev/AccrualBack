package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Type;
import uce.edu.ec.accrual.models.repository.TypeRepository;
import uce.edu.ec.accrual.models.service.TypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<Type>> docents = Optional.of((List<Type>) repository.findAll());
        return docents.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idType) {
        Optional<Type> docent = repository.findById(idType);
        return docent.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Type()));
    }

    @Override
    public ResponseEntity<?> save(Type type) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Type type) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long idType) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Type type, Long idType) {
        return null;
    }
}
