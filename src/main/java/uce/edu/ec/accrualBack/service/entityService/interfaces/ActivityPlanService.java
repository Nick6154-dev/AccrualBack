package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.ActivityPlan;

import java.util.List;

public interface ActivityPlanService {

    List<ActivityPlan> findAll();

    List<ActivityPlan> findActivityPlansByIdPlan(Long idPlan);

    List<ActivityPlan> findActivityPlansByState(Integer state);

    boolean existsByIdPlan(Long idPlan);

    ActivityPlan findById(Long idActivityPlan);

    ActivityPlan save(ActivityPlan activityPlan);

    String delete(ActivityPlan activityPlan);

    String deleteById(Long idActivityPlan);

    ActivityPlan update(ActivityPlan activityPlan, Long idActivityPlan);

}
