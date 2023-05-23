package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.object.ValidatorObject;

import java.util.List;

public interface ValidatorService {

    List<ValidatorObject> findAllPersonDocentPlan();

    List<ValidatorObject> findByState(Integer state);

    ValidatorObject findPlansByPerson(Long idPerson);

    String validatePlanByPerson(Plan plan);

}
