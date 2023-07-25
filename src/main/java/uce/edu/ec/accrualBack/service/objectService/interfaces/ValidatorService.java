package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.object.ValidatorObject;

import java.util.List;
import java.util.Map;

public interface ValidatorService {

    List<ValidatorObject> findAllPersonDocentPlan();

    ValidatorObject findPlansByPerson(Long idPerson);

    Map<Integer, String> validatePlanByPerson(Map<String, String> newValues);

    Map<Integer, String> approveAllPlans();

    byte[] generateExcelActivitiesPlan(Long idPerson, Long idPlan);

    byte[] generateExcelDocentsInPlan();

    byte[] generateExcelSelectDocentsActivitiesPlan(List<Long> idsPeople);

}
