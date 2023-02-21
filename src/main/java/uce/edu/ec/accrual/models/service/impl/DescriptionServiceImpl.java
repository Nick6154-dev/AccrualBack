package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Description;
import uce.edu.ec.accrual.models.repository.DescriptionRepository;
import uce.edu.ec.accrual.models.service.DescriptionService;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private DescriptionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<Description>> docents = Optional.of((List<Description>) repository.findAll());
        return docents.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idDescription) {
        Optional<Description> docent = repository.findById(idDescription);
        return docent.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findDescriptionByActivityPlan(ActivityPlan activityPlan) {
        Optional<Description> description = repository.findDescriptionByActivityPlan(activityPlan);
        return description.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> save(Description description) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> delete(Description description) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> deleteById(Long idDescription) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> update(Description description, Long idDescription) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
