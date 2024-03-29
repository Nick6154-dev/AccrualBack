package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;

import java.util.Map;

public interface PlanInstitutionActivityService {

    void temporalMethod();

    Map<Integer, String> addNewActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution);

    Map<Integer, String> deleteActivityWithInstitution(Long idActivityPlan);

    Map<Integer, String> updateActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan);

    Map<Integer, String> validateActivitiesByIdActivityPlan(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan);

    Map<Integer, Object> findActivitiesPlanByPlan(Long idPerson, Long idPeriod);

}
