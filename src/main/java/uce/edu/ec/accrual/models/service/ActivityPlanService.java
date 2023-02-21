package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.ActivityPlan;

public interface ActivityPlanService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idActivityPlan);

    ResponseEntity<?> findActivityPlansByIdPlan(Long idPlan);

    ResponseEntity<?> save(ActivityPlan activityPlan);

    ResponseEntity<?> delete(ActivityPlan activityPlan);

    ResponseEntity<?> deleteById(Long idActivityPlan);

    ResponseEntity<?> update(ActivityPlan activityPlan, Long idActivityPlan);

}
