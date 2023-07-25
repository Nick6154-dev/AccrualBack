package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.StatePlan;
import uce.edu.ec.accrualBack.repository.StatePlanRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.StatePlanService;

import java.util.List;

@Service
public class StatePlanServiceImpl implements StatePlanService {

    @Autowired
    private StatePlanRepository statePlanRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StatePlan> findAll() {
        return statePlanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public StatePlan findById(String idStatePlan) {
        return statePlanRepository.findById(idStatePlan).orElseGet(StatePlan::new);
    }

    @Override
    @Transactional(readOnly = true)
    public StatePlan findByIdPlan(Long idPlan) {
        return statePlanRepository.findByIdPlan(idPlan).orElseGet(StatePlan::new);
    }

    @Override
    @Transactional
    public StatePlan save(StatePlan statePlan) {
        return statePlanRepository.save(statePlan);
    }

    @Override
    @Transactional
    public List<StatePlan> saveAll(List<StatePlan> statePlans) {
        return statePlanRepository.saveAll(statePlans);
    }

    @Override
    @Transactional
    public void deleteById(String idStatePlan) {
        statePlanRepository.deleteById(idStatePlan);
    }

    @Override
    @Transactional
    public void delete(StatePlan statePlan) {
        statePlanRepository.delete(statePlan);
    }

    @Override
    @Transactional
    public void deleteByIdPlan(Long idPlan) {
        statePlanRepository.deleteByIdPlan(idPlan);
    }
}
