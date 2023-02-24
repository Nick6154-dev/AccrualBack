package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Plan;

public interface PlanService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idPlan);

    ResponseEntity<?> findByIdPersonAndPeriod(Long idPerson, String period);

    ResponseEntity<?> save(Plan plan);

    ResponseEntity<?> deleteById(Long idPlan);

    ResponseEntity<?> update(Plan plan, Long idPlan);

}
