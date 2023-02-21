package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.repository.ActivityPlanRepository;
import uce.edu.ec.accrual.models.service.ActivityPlanService;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    @Autowired
    private ActivityPlanRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<ActivityPlan>> docents = Optional.of((List<ActivityPlan>) repository.findAll());
        return docents.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idActivityPlan) {
        Optional<ActivityPlan> docent = repository.findById(idActivityPlan);
        return docent.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findActivityPlansByIdPlan(Long idPlan) {
        return repository.findActivityPlansByIdPlan(idPlan).map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> save(ActivityPlan activityPlan) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> delete(ActivityPlan activityPlan) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> deleteById(Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> update(ActivityPlan activityPlan, Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
