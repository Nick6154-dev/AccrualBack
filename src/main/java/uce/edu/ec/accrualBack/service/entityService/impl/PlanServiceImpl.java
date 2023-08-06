package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.repository.PlanRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PlanService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository repository;

    @Autowired
    private DocentService docentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PeriodService periodService;

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
    public Plan findByIdPersonAndPeriod(Long idPerson, Period period) {
        return Optional.of(docentService.findByIdPerson(idPerson)).map(value -> repository.findPlanByPeriodAndIdDocent
                (period, value.getIdDocent()).orElseGet(Plan::new)).orElseGet(Plan::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Plan> findByDocent(Docent docent) {
        return repository.findPlansByIdDocent(docent.getIdDocent()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Plan> findPlansByStateIs(Integer state) {
        return repository.findPlansByStateIs(state).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public Plan save(Plan plan) {
        return repository.findPlanByPeriodAndIdDocent(plan.getPeriod(), plan.getIdDocent())
                .orElseGet(() -> {
                    Optional<Plan> optionalPlan = repository.findNextNumberPlanByIdDocent(plan.getIdDocent());
                    if (optionalPlan.isPresent()) {
                        plan.setNumberPlan(optionalPlan.get().getNumberPlan() + 1);
                    } else {
                        plan.setNumberPlan(1);
                    }
                    plan.setStarDate(LocalDate.now());
                    plan.setState(false);
                    plan.setEditable(true);
                    plan.setObservations("NA");
                    return repository.save(plan);
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
    @Transactional
    public String updateNotEditable(Long idPerson, Long idPeriod) {
        Period period = periodService.findById(idPeriod);
        return Optional.of(docentService.findByIdPerson(idPerson))
                .map(docent -> repository.findPlanByPeriodAndIdDocent(period, docent.getIdDocent())
                        .map(value -> {
                            value.setEditable(false);
                            repository.save(value);
                            mailService.sendPlanNotificationMail(idPerson);
                            return "Este plan ya no se podra editar";
                        }).orElseGet(() -> "No se pudo encontrar un plan"))
                .orElseGet(() -> "No se ha encontrado un plan asignado a ese id persona");
    }

    @Override
    public String setPlansEditableByPeriod(Period period) {
        List<Plan> plans = repository.findAllByEditableIsAndPeriod(false, period).orElseGet(ArrayList::new);
        if (plans.isEmpty()) return "No hay planes por actualizar";
        for (Plan plan : plans) {
            plan.setEditable(true);
            repository.save(plan);
        }
        return "Planes actualizados, ya se pueden activar las evidencias";
    }

    @Override
    public boolean existsPlanByPeriod(Period period) {
        return repository.existsPlanByPeriod(period);
    }

}
