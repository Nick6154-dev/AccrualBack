package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;

public interface PlanInstitutionActivityService {

    String addNewActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution);

    String deleteActivityWithInstitution(Long idActivityPlan);

    String updateActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan);

    String validateActivitiesByIdActivityPlan(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan);

}
