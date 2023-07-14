package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.object.ValidatorObject;

import java.util.List;

public interface ValidatorService {

    List<ValidatorObject> findAllPersonDocentPlan();

    ValidatorObject findPlansByPerson(Long idPerson);

    String validatePlanByPerson(Plan plan);

    void approveAllPlans();

    byte[] generateExcelActivitiesPlan(Long idPerson, Long idPlan);

    byte[] generateExcelDocentsInPlan();

    byte[] generateExcelSelectDocentsActivitiesPlan(List<ValidatorObject> validatorsObjects);

}
