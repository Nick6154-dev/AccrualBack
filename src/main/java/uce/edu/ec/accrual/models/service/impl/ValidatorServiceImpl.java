package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Person;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.object.ValidatorObject;
import uce.edu.ec.accrual.models.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PersonService personService;

    @Autowired
    private DocentService docentService;

    @Override
    public List<ValidatorObject> findAllPersonDocentPlan() {
        List<ValidatorObject> validatorObjects = new ArrayList<>();
        List<Person> people = personService.findAll();
        ValidatorObject validatorObject;
        for (Person p : people) {
            validatorObject = new ValidatorObject();
            validatorObject.setPerson(p);
            Optional<Docent> docent = Optional.of(docentService.findByIdPerson(p.getIdPerson()));
            if (docent.get().getIdDocent() == null || docent.get().getIdDocent() == 0) continue;
            validatorObject.setDocent(docent.get());
            Optional<List<Plan>> plans = Optional.of(planService.findByDocent(validatorObject.getDocent()));
            if (plans.get().isEmpty()) continue;
            validatorObject.setPlans(plans.get());
            validatorObjects.add(validatorObject);
        }
        return validatorObjects;
    }

    @Override
    public List<ValidatorObject> findByState(Integer state) {
        return null;
    }

    @Override
    public ValidatorObject findPlansByPerson(Long idPerson) {
        ValidatorObject validatorObject = new ValidatorObject();
        Optional<Docent> docent = Optional.ofNullable(docentService.findByIdPerson(idPerson));
        docent.flatMap(value -> Optional.ofNullable(planService.findByDocent(value))).ifPresent(validatorObject::setPlans);
        return validatorObject;
    }

    @Override
    public String validatePlanByPerson(Plan plan) {
        return Optional.of(planService.findById(plan.getIdPlan())).map(value -> {
            value.setState(plan.getState());
            value.setObservations(plan.getObservations());
            planService.save(value);
            return "Estado de plan actualizado junto con las observaciones";
        }).orElseGet(() -> "No se encontro el id del plan para la actualizacion");
    }

}
