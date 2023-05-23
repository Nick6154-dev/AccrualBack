package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.repository.PlanRepository;
import uce.edu.ec.accrual.models.service.DocentService;
import uce.edu.ec.accrual.models.service.PlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository repository;

    @Autowired
    private DocentService docentService;

    @Override
    @Transactional(readOnly = true)
    public List<Plan> findAll() {
        return Optional.of((List<Plan>) repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Plan findById(Long idPlan) {
        return repository.findById(idPlan).orElseGet(Plan::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Plan findByIdPersonAndPeriod(Long idPerson, String period) {
        return Optional.of(docentService.findByIdPerson(idPerson)).map(value -> {
            return repository.findByPeriodAndIdDocent(period, value.getIdDocent()).orElseGet(Plan::new);
        }).orElseGet(Plan::new);
    }

    @Override
    public List<Plan> findByDocent(Docent docent) {
        return repository.findPlansByIdDocent(docent.getIdDocent()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public Plan save(Plan plan) {
        return repository.findByPeriodAndIdDocent(plan.getPeriod().getValuePeriod(), plan.getIdDocent())
                .orElseGet(() -> {
                    Optional<Plan> optionalPlan = repository.findNextNumberPlanByIdDocent(plan.getIdDocent());
                    return optionalPlan.map(value -> {
                        plan.setNumberPlan(value.getNumberPlan() + 1);
                        return repository.save(plan);
                    }).orElseGet(() -> {
                        plan.setNumberPlan(1);
                        return repository.save(plan);
                    });
                });
    }

    @Override
    @Transactional
    public String deleteById(Long idPlan) {
        return repository.findById(idPlan).map(value -> {
            repository.deleteById(idPlan);
            return "Eliminado con exito";
        }).orElseGet(() -> "No se ha podido eliminar");
    }

    @Override
    @Transactional
    public Plan update(Plan plan, Long idPlan) {
        return repository.findById(idPlan).map(value -> repository.save(plan)).orElseGet(Plan::new);
    }

    @Override
    public String updateNotEditable(Long idPerson, String period) {
        return Optional.of(docentService.findByIdPerson(idPerson))
                .map(docent -> repository.findByPeriodAndIdDocent(period, docent.getIdDocent())
                        .map(value -> {
                            value.setEditable(false);
                            repository.save(value);
                            return "Este plan ya no se podra editar";
                        }).orElseGet(() -> "No se pudo encontrar un plan"))
                .orElseGet(() -> "No se ha encontrado un plan asignado a ese id persona");
    }
}
