package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.PlanActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanActivityService;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlanActivityServiceImpl implements PlanActivityService {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private SubtypeService subtypeService;

    @Autowired
    private PlanService planService;

    @Override
    @Transactional
    public ActivityPlan save(PlanActivity activityPlanAccrual) {
        Type type = typeService.findById(activityPlanAccrual.getIdActivityType());
        Subtype subtype = subtypeService.findById(activityPlanAccrual.getIdActivitySubtype());
        Plan plan = planService.findById(activityPlanAccrual.getIdPlan());
        if (plan != null) {
            if (type != null && subtype != null) {
                if (!Objects.equals(subtype.getActivityType().getIdActivityType(), type.getIdActivityType())) {
                    return new ActivityPlan();
                }
                Activity activity = loadActivity(activityPlanAccrual, new Activity());
                ActivityPlan activityPlan = loadPlanAccrual(activityPlanAccrual, activity, type, subtype, new ActivityPlan());
                if (type.getIdActivityType().intValue() == 2) {
                    loadDescription(activityPlanAccrual, activityPlan, new Description());
                }
                return activityPlan;
            } else {
                return new ActivityPlan();
            }
        }
        return new ActivityPlan();
    }

    @Override
    @Transactional
    public String deleteByIdActivityPlan(Long idActivityPlan) {
        return Optional.of(activityPlanService.findById(idActivityPlan)).map(value -> {
            if (value.getType().getIdActivityType().intValue() == 2) {
                if (descriptionService.findDescriptionByActivityPlan(value) != null) {
                    descriptionService.delete(descriptionService.findDescriptionByActivityPlan(value));
                }
            }
            activityService.delete(value.getActivity());
            activityPlanService.delete(value);
            return "Eliminado con exito";
        }).orElse("El id especificado no se encuentra en el sistema");
    }

    @Override
    @Transactional
    public String update(PlanActivity activityPlanAccrual, Long idActivityPlan) {
        return Optional.of(activityPlanService.findById(idActivityPlan)).map(value -> {
            if (typeService.findById(activityPlanAccrual.getIdActivityType()) != null &&
                    subtypeService.findById(activityPlanAccrual.getIdActivitySubtype()) != null) {
                if (!Objects.equals(subtypeService.findById(
                                activityPlanAccrual.getIdActivitySubtype()).getActivityType().getIdActivityType(),
                        typeService.findById(activityPlanAccrual.getIdActivityType()).getIdActivityType())) {
                    return "El subtipo de actividad no pertenece";
                }
                if (value.getType().getIdActivityType().intValue() == 2
                        && descriptionService.findDescriptionByActivityPlan(value) != null) {
                    loadDescription(activityPlanAccrual, value, descriptionService.findDescriptionByActivityPlan(value));
                }
                loadPlanAccrual(activityPlanAccrual, loadActivity(activityPlanAccrual, value.getActivity()),
                        typeService.findById(activityPlanAccrual.getIdActivityType()),
                        subtypeService.findById(activityPlanAccrual.getIdActivitySubtype()), value);
                return "Actividad actualizada correctamente";
            } else {
                return "Problemas al cargar el tipo o subtipo enviados";
            }
        }).orElse("El id especificado no se encuentra en el sistema");
    }

    @Transactional
    public Activity loadActivity(PlanActivity activityPlanAccrual, Activity activity) {
        activity.setStartDate(activityPlanAccrual.getStarDate());
        activity.setEndDate(activityPlanAccrual.getEndDate());
        activity.setDescription(activityPlanAccrual.getDescriptionActivity());
        activity.setEvidences(activityPlanAccrual.getEvidences());
        return activityService.save(activity);
    }

    @Transactional
    public ActivityPlan loadPlanAccrual(PlanActivity activityPlanAccrual, Activity activity,
                                        Type type, Subtype subtype, ActivityPlan activityPlan) {
        activityPlan.setIdPlan(activityPlanAccrual.getIdPlan());
        activityPlan.setActivity(activity);
        activityPlan.setType(type);
        activityPlan.setSubtype(subtype);
        activityPlan.setState(0);
        return activityPlanService.save(activityPlan);
    }

    @Transactional
    public void loadDescription(PlanActivity activityPlanAccrual, ActivityPlan activityPlan, Description description) {
        description.setActivityPlan(activityPlan);
        description.setDescription(activityPlanAccrual.getDescriptionSubtype());
        descriptionService.save(description);
    }


}
