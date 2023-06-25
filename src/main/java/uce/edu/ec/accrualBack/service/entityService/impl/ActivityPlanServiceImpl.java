package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.repository.ActivityPlanRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.ActivityPlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    @Autowired
    private ActivityPlanRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ActivityPlan> findAll() {
        return Optional.of((List<ActivityPlan>) repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityPlan> findActivityPlansByIdPlan(Long idPlan) {
        return repository.findActivityPlansByIdPlan(idPlan).orElseGet(ArrayList::new);
    }

    @Override
    public List<ActivityPlan> findActivityPlansByState(Integer state) {
        return repository.findActivityPlansByState(state).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ActivityPlan findById(Long idActivityPlan) {
        return repository.findById(idActivityPlan).orElseGet(ActivityPlan::new);
    }

    @Override
    public ActivityPlan save(ActivityPlan activityPlan) {
        return repository.save(activityPlan);
    }

    @Override
    public String delete(ActivityPlan activityPlan) {
        repository.delete(activityPlan);
        return "Actividad de plan eliminada con exito";
    }

    @Override
    public String deleteById(Long idActivityPlan) {
        repository.deleteById(idActivityPlan);
        return "Actividad de plan eliminada con exito";
    }

    @Override
    public ActivityPlan update(ActivityPlan activityPlan, Long idActivityPlan) {
        return Optional.of(repository.findById(idActivityPlan)).map(value -> {
            activityPlan.setIdActivityPlan(idActivityPlan);
            return repository.save(activityPlan);
        }).orElseGet(ActivityPlan::new);
    }

}
