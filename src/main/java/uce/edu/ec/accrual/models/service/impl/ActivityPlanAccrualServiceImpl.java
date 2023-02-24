package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.*;
import uce.edu.ec.accrual.models.object.ActivityPlanAccrual;
import uce.edu.ec.accrual.models.repository.*;
import uce.edu.ec.accrual.models.service.ActivityPlanAccrualService;

import java.util.Objects;
import java.util.Optional;

@Service
public class ActivityPlanAccrualServiceImpl implements ActivityPlanAccrualService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityPlanRepository activityPlanRepository;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private SubtypeRepository subtypeRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    @Transactional
    public ResponseEntity<?> save(ActivityPlanAccrual activityPlanAccrual) {
        Optional<Type> type = typeRepository.findById(activityPlanAccrual.getIdActivityType());
        Optional<Subtype> subtype = subtypeRepository.findById(activityPlanAccrual.getIdActivitySubtype());
        Optional<Plan> plan = planRepository.findById(activityPlanAccrual.getIdPlan());
        if (plan.isPresent()) {
            if (type.isPresent() && subtype.isPresent()) {
                if (!Objects.equals(subtype.get().getActivityType().getIdActivityType(), type.get().getIdActivityType())) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El subtipo de actividad no pertenece " +
                            "al tipo de actividad");
                }
                Activity activity = loadActivity(activityPlanAccrual, new Activity());
                ActivityPlan activityPlan = loadPlanAccrual(activityPlanAccrual, activity, type.get(), subtype.get(), new ActivityPlan());
                if (type.get().getIdActivityType().intValue() == 2) {
                    loadDescription(activityPlanAccrual, activityPlan, new Description());
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(activityPlan);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body("Los valores definidos en el tipo de actividad o" +
                        " el subtipo no son correctos");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id del plan definido no existe");
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteByIdActivityPlan(Long idActivityPlan) {
        return activityPlanRepository.findById(idActivityPlan).map(value -> {
            if (value.getType().getIdActivityType().intValue() == 2 && descriptionRepository.findDescriptionByActivityPlan(value).isPresent()) {
                descriptionRepository.delete(descriptionRepository.findDescriptionByActivityPlan(value).get());
            }
            activityRepository.delete(value.getActivity());
            activityPlanRepository.delete(value);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id especificado no se encuentra en el sistema"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(ActivityPlanAccrual activityPlanAccrual, Long idActivityPlan) {
        return activityPlanRepository.findById(idActivityPlan).map(value -> {
            if (typeRepository.findById(activityPlanAccrual.getIdActivityType()).isPresent() &&
                    subtypeRepository.findById(activityPlanAccrual.getIdActivitySubtype()).isPresent()) {
                if (!Objects.equals(subtypeRepository.findById(activityPlanAccrual.getIdActivitySubtype()).get().getActivityType().getIdActivityType(),
                        typeRepository.findById(activityPlanAccrual.getIdActivityType()).get().getIdActivityType())) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El subtipo de actividad no pertenece " +
                            "al tipo de actividad");
                }
                if (value.getType().getIdActivityType().intValue() == 2 && descriptionRepository.findDescriptionByActivityPlan(value).isPresent()) {
                    loadDescription(activityPlanAccrual, value, descriptionRepository.findDescriptionByActivityPlan(value).get());
                }
                loadPlanAccrual(activityPlanAccrual, loadActivity(activityPlanAccrual, value.getActivity()),
                        typeRepository.findById(activityPlanAccrual.getIdActivityType()).orElse(new Type()),
                        subtypeRepository.findById(activityPlanAccrual.getIdActivitySubtype()).orElse(new Subtype()), value);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actividad actualizada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al cargar el tipo o subtipo enviados");
            }
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id especificado no se encuentra en el sistema"));
    }

    @Transactional
    public Activity loadActivity(ActivityPlanAccrual activityPlanAccrual, Activity activity) {
        activity.setStartDate(activityPlanAccrual.getStarDate());
        activity.setEndDate(activityPlanAccrual.getEndDate());
        activity.setDescription(activityPlanAccrual.getDescriptionActivity());
        activity.setEvidences(activityPlanAccrual.getEvidences());
        return activityRepository.save(activity);
    }

    @Transactional
    public ActivityPlan loadPlanAccrual(ActivityPlanAccrual activityPlanAccrual, Activity activity,
                                        Type type, Subtype subtype, ActivityPlan activityPlan) {
        activityPlan.setIdPlan(activityPlanAccrual.getIdPlan());
        activityPlan.setActivity(activity);
        activityPlan.setType(type);
        activityPlan.setSubtype(subtype);
        activityPlan.setState(0);
        return activityPlanRepository.save(activityPlan);
    }

    @Transactional
    public void loadDescription(ActivityPlanAccrual activityPlanAccrual, ActivityPlan activityPlan, Description description) {
        description.setActivityPlan(activityPlan);
        description.setDescription(activityPlanAccrual.getDescriptionSubtype());
        descriptionRepository.save(description);
    }

}
