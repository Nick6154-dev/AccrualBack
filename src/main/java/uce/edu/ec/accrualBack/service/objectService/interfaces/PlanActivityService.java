package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.object.PlanActivity;

public interface PlanActivityService {

    ActivityPlan save(PlanActivity activityPlanAccrual);

    String deleteByIdActivityPlan(Long idActivityPlan);

    String update(PlanActivity activityPlanAccrual, Long idActivityPlan);

}
