package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.object.ActivityInstitutionJoin;
import uce.edu.ec.accrualBack.object.Converter;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.InstitutionActivityService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanActivityService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PlanInstitutionActivityServiceImpl implements PlanInstitutionActivityService {

    @Autowired
    private PlanActivityService planActivityService;

    @Autowired
    private InstitutionActivityService institutionActivityService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PeriodService periodService;

    @Override
    public String addNewActivityWithInstitution(Converter converter) {
        ActivityInstitutionJoin activityInstitutionJoin = new ActivityInstitutionJoin(converter);
        Docent docent = docentService.findByIdPerson(activityInstitutionJoin.getIdPerson());
        if (docent != null) {
            Plan plan = new Plan();
            plan.setPeriod(periodService.findById(activityInstitutionJoin.getIdPeriod()));
            plan.setIdDocent(docent.getIdDocent());
            plan.setStarDate(LocalDate.now());
            plan.setEditable(true);
            plan = planService.save(plan);
            if (plan != null) {
                if (!plan.getEditable()) {
                    return "Ya no se agregan mas actividades pues ya no es editable";
                }
                activityInstitutionJoin.getActivityPlanAccrual().setIdPlan(plan.getIdPlan());
                ActivityPlan activityPlan = planActivityService.save(activityInstitutionJoin.getActivityPlanAccrual());
                if (activityPlan == null) {
                    return "Problemas al cargar el plan activity";
                }
                activityInstitutionJoin.getInstitutionActivity().setIdActivity(activityPlan.getActivity().getIdActivity());
                institutionActivityService.save(activityInstitutionJoin.getInstitutionActivity());
                return "ACtividad nueva agregada";
            } else {
                return "Problemas al cargar el plan";
            }
        }
        return "El id no pertenece a ningun docente";
    }

    @Override
    public String deleteActivityWithInstitution(Long idActivityPlan) {
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan != null) {
            Plan plan = planService.findById(activityPlan.getIdPlan());
            if (plan != null) {
                if (!plan.getEditable()) {
                    return "Ya no se eliminan mas actividades pues ya no es editable";
                }
            }
            String response = planActivityService.deleteByIdActivityPlan(idActivityPlan);
            Optional<Institution> institution = Optional.of(institutionService
                    .findInstitutionByActivity(activityPlan.getActivity().getIdActivity()));
            institution.ifPresent(value -> institutionActivityService.deleteById(value.getIdInstitution()));
            return response;
        }
        return "Problemas al eliminar";
    }

    @Override
    public String updateActivityWithInstitution(Converter converter, Long idActivityPlan) {
        ActivityInstitutionJoin activityInstitutionJoin = new ActivityInstitutionJoin(converter);
        Docent docent = docentService.findByIdPerson(activityInstitutionJoin.getIdPerson());
        if (docent != null) {
            ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
            if (activityPlan != null) {
                Plan plan = planService.findById(activityPlan.getIdPlan());
                if (plan != null) {
                    if (!plan.getEditable()) {
                        return "Ya no se actualizan mas actividades pues ya no es editable";
                    }
                }
                activityInstitutionJoin.getActivityPlanAccrual().setIdPlan(activityPlan.getIdPlan());
                String response = planActivityService.update(activityInstitutionJoin.getActivityPlanAccrual(), idActivityPlan);
                activityInstitutionJoin.getInstitutionActivity().setIdActivity(activityPlan.getActivity().getIdActivity());
                Optional<Institution> institution = Optional.of(institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity()));
                institution.ifPresent(value -> institutionActivityService.update(activityInstitutionJoin.getInstitutionActivity(), value.getIdInstitution()));
                return response;
            }
            return "Problemas al actualizar";
        }
        return "Id de persona no encontrado";
    }

}
