package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.StatePlan;

import java.util.List;

public interface StatePlanService {

    List<StatePlan> findAll();

    StatePlan findById(String idStatePlan);

    StatePlan findByIdPlan(Long idPlan);

    StatePlan save(StatePlan statePlan);

    List<StatePlan> saveAll(List<StatePlan> statePlans);

    void deleteById(String idStatePlan);

    void delete(StatePlan statePlan);

    void deleteByIdPlan(Long idPlan);

}
