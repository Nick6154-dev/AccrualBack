package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Activity;
import uce.edu.ec.accrual.models.repository.ActivityRepository;
import uce.edu.ec.accrual.models.service.ActivityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<Activity>> docents = Optional.of((List<Activity>) repository.findAll());
        return docents.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idActivity) {
        Optional<Activity> docent = repository.findById(idActivity);
        return docent.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Activity()));
    }

    @Override
    public ResponseEntity<?> save(Activity activity) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> delete(Activity activity) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> deleteById(Long idActivity) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<?> update(Activity activity, Long idActivity) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
