package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> findAll();

    Plan findById(Long idPlan);

    Plan findByIdPersonAndPeriod(Long idPerson, String period);

    List<Plan> findByDocent(Docent docent);

    List<Plan> findPlansByStateIs(Integer state);

    Plan save(Plan plan);

    String deleteById(Long idPlan);

    Plan update(Plan plan, Long idPlan);

    String updateNotEditable(Long idPerson, String period);

}
