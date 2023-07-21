package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.repository.AccrualDataRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.AccrualDataService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.ActivityPlanService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PlanService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;

import java.util.*;

@Service
public class AccrualDataServiceImpl implements AccrualDataService {

    @Autowired
    private AccrualDataRepository repository;

    @Autowired
    private DocentService docentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PlanService planService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Override
    @Transactional(readOnly = true)
    public List<AccrualData> findAll() {
        return (List<AccrualData>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccrualData> findAllByAccrualData(boolean settlement) {
        return repository.findAllBySettlement(settlement);
    }

    @Override
    @Transactional(readOnly = true)
    public AccrualData findById(Long idAccrualData) {
        return repository.findById(idAccrualData).orElseGet(AccrualData::new);
    }

    @Override
    @Transactional(readOnly = true)
    public AccrualData findByDocent(Docent docent) {
        return repository.findAccrualDataByDocent(docent).orElseGet(AccrualData::new);
    }

    @Override
    @Transactional
    public AccrualData save(AccrualData accrualData) {
        return repository.findAccrualDataByDocent(accrualData.getDocent()).orElseGet(() -> repository.save(accrualData));
    }

    @Override
    @Transactional
    public String deleteById(Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> "Eliminado").orElseGet(() -> "No se ha podido eliminar");
    }

    @Override
    @Transactional
    public AccrualData update(AccrualData accrualData, Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> {
            accrualData.setIdAccrualData(value.getIdAccrualData());
            return repository.save(accrualData);
        }).orElseGet(AccrualData::new);
    }

    @Override
    @Transactional
    public String updateObservations(String observations, Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> {
            value.setObservations(observations);
            repository.save(value);
            return "Observacion guardada con exito";
        }).orElseGet(() -> "Observacion no guardada");
    }

    @Override
    @Transactional
    public void updateSettlement(Boolean settlement, AccrualData accrualData) {
        accrualData.setSettlement(settlement);
        repository.save(accrualData);
    }

    @Override
    @Transactional
    public Map<Integer, String> approveSettlement(Long idPerson) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        Optional<AccrualData> accrualData = repository.findAccrualDataByDocent(docent);
        if (accrualData.isPresent()) {
            List<Plan> plans = planService.findByDocent(accrualData.get().getDocent());
            if (plans.isEmpty()) {
                response.put(400, "No se puede enviar la solicitud, no tiene planes cargados");
                return response;
            }
            for (Plan plan : plans) {
                List<ActivityPlan> activityPlans = activityPlanService.findActivityPlansByIdPlan(plan.getIdPlan());
                if (activityPlans.isEmpty()) {
                    response.put(400, "No se puede enviar la solicitud, no tiene actividades asignadas al periodo "
                            + plan.getPeriod().getValuePeriod());
                    return response;
                }
            }
            this.updateSettlement(true, accrualData.get());
            mailService.sendSettlementNotificationMail(accrualData.get().getDocent().getIdPerson());
            response.put(200, "Finiquito aprovado y mail enviado");
            return response;
        }
        response.put(400, "No se a encontrado datos de devengamiento de este docente, porfavor cargar esos datos");
        return response;
    }

}
