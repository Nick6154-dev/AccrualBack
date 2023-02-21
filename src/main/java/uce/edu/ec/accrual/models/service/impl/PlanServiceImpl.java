package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.repository.PlanRepository;
import uce.edu.ec.accrual.models.service.PlanService;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<Plan>> plans = Optional.of((List<Plan>) repository.findAll());
        return plans.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idPlan) {
        Optional<Plan> plan = repository.findById(idPlan);
        return plan.map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Plan plan) {
        Optional<Plan> optionalPlan = repository.findByPeriodAndIdDocent(plan.getPeriod(), plan.getIdDocent());
        return optionalPlan.map(value -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(value))
                .orElseGet(() -> {
                    Optional<Plan> optionalPlan1 = repository.findNextNumberPlanByIdDocent(plan.getIdDocent());
                    return optionalPlan1.map(value2 -> {
                        plan.setNumberPlan(value2.getNumberPlan() + 1);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(plan));
                    }).orElseGet(() -> {
                        plan.setNumberPlan(1);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(plan));
                    });
                });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idPlan) {
        Optional<Plan> optionalPlan = repository.findById(idPlan);
        return optionalPlan.map(value -> {
            repository.deleteById(idPlan);
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Plan plan, Long idPlan) {
        Optional<Plan> optionalPlan = repository.findById(idPlan);
        return optionalPlan.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(plan)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
