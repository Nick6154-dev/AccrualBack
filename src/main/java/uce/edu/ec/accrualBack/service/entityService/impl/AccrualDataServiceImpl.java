package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.repository.AccrualDataRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccrualDataServiceImpl implements AccrualDataService {

    @Autowired
    private AccrualDataRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PlanService planService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private SettlementDocentService settlementDocentService;

    @Override
    @Transactional(readOnly = true)
    public List<AccrualData> findAll() {
        return (List<AccrualData>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllPeopleSettlementRequest() {
        List<SettlementDocent> settlementDocents = settlementDocentService.findAll();
        return settlementDocents.stream()
                .map(settlementDocent -> docentService.findById(settlementDocent.getIdDocent()))
                .map(docent -> personService.findById(docent.getIdPerson()))
                .collect(Collectors.toList());
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
    public Map<Integer, String> save(AccrualData accrualData, Long idPerson) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getIdDocent() == null) {
            response.put(400, "No se ha encontrado el docente");
            return response;
        }
        if (findByDocent(docent).getIdAccrualData() != null) {
            response.put(400, "Ya existen datos de devengamiento asignados al docente");
            return response;
        }
        accrualData.setDocent(docent);
        accrualData.setSettlement(false);
        accrualData.setObservations("NA");
        repository.save(accrualData);
        response.put(200, "Datos de devengamiento guardados exitosamente");
        return response;
    }

    @Override
    @Transactional
    public String deleteById(Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> "Eliminado").orElseGet(() -> "No se ha podido eliminar");
    }

    @Override
    @Transactional
    public Map<Integer, String> update(AccrualData accrualData, Long idAccrualData) {
        Map<Integer, String> response = new HashMap<>();
        AccrualData oldAccrualData = findById(idAccrualData);
        if (oldAccrualData.getIdAccrualData() == null) {
            response.put(400, "No se ha encontrado datos de devengamiento");
            return response;
        }
        accrualData.setDocent(oldAccrualData.getDocent());
        accrualData.setSettlement(oldAccrualData.getSettlement());
        accrualData.setObservations(oldAccrualData.getObservations());
        accrualData.setIdAccrualData(oldAccrualData.getIdAccrualData());
        repository.save(accrualData);
        response.put(200, "Datos de devengamiento actualizados correctamente");
        return response;
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
    public Map<Integer, String> requestApproval(Long idPerson) {
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
            settlementDocentService.save(new SettlementDocent(accrualData.get().getDocent().getIdDocent()));
            mailService.sendSettlementNotificationMail(accrualData.get().getDocent().getIdPerson());
            response.put(200, "Solicitud de finiquito guardada y mail enviado");
            return response;
        }
        response.put(400, "No se a encontrado datos de devengamiento de este docente, porfavor cargar esos datos");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> approveSettlement(Long idPerson, boolean approved) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        if (settlementDocentService.existsByIdDocent(docent.getIdDocent())) {
            settlementDocentService.deleteByIdDocent(docent.getIdDocent());
            if (approved) {
                mailService.sendSettlementApproveMail(idPerson, true);
                response.put(200, "La solicitud ha sido aprobada y el estado de finiquito ha cambiado exitosamente.");
            } else {
                mailService.sendSettlementApproveMail(idPerson, false);
                response.put(200, "La solicitud ha sido denegada y el estado de finiquito ha cambiado exitosamente.");
            }
            AccrualData accrualData = findByDocent(docent);
            updateSettlement(approved, accrualData);
            mailService.sendSettlementApproveMail(idPerson, approved);
            return response;
        }
        response.put(400, "El docente no ha enviado solicitud para finiquito.");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> approveAllRequestSettlement() {
        List<SettlementDocent> settlementDocents = settlementDocentService.findAll();
        Map<Integer, String> response = new HashMap<>();
        for (SettlementDocent settlementDocent : settlementDocents) {
            Docent docent = docentService.findById(settlementDocent.getIdDocent());
            Map<Integer, String> aux = approveSettlement(docent.getIdPerson(), true);
            response.putAll(aux);
        }
        return response;
    }

}
