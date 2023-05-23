package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.ActivityPlan;

import java.util.List;

public interface ActivityPlanService {

    List<ActivityPlan> findAll();

    List<ActivityPlan> findActivityPlansByIdPlan(Long idPlan);

    List<ActivityPlan> findActivityPlansByState(Integer state);

    ActivityPlan findById(Long idActivityPlan);

    ActivityPlan save(ActivityPlan activityPlan);

    String delete(ActivityPlan activityPlan);

    String deleteById(Long idActivityPlan);

    ActivityPlan update(ActivityPlan activityPlan, Long idActivityPlan);

}
