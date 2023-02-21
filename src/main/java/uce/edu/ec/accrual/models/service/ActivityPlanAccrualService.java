package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.object.ActivityPlanAccrual;

public interface ActivityPlanAccrualService {

    ResponseEntity<?> save(ActivityPlanAccrual activityPlanAccrual);

    ResponseEntity<?> deleteByIdActivityPlan(Long idActivityPlan);

    ResponseEntity<?> update(ActivityPlanAccrual activityPlanAccrual, Long idActivityPlan);

}
