package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> findAll();

    Plan findById(Long idPlan);

    Plan findByIdPersonAndPeriod(Long idPerson, Period period);

    List<Plan> findByDocent(Docent docent);

    List<Plan> findPlansByStateIs(Integer state);

    Plan save(Plan plan);

    String deleteById(Long idPlan);

    Plan update(Plan plan, Long idPlan);

    String updateNotEditable(Long idPerson, Long idPeriod);

    String setPlansEditableByPeriod(Period period);

}
