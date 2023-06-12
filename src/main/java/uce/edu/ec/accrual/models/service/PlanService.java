package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Plan;

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
