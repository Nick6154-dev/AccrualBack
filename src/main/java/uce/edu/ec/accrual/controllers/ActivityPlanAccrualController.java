package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.object.ActivityInstitutionJoin;
import uce.edu.ec.accrual.models.object.Converter;
import uce.edu.ec.accrual.models.repository.ActivityPlanRepository;
import uce.edu.ec.accrual.models.repository.DocentRepository;
import uce.edu.ec.accrual.models.repository.InstitutionRepository;
import uce.edu.ec.accrual.models.service.ActivityPlanAccrualService;
import uce.edu.ec.accrual.models.service.InstitutionActivityService;
import uce.edu.ec.accrual.models.service.PlanService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/activityPlanAccrual")
public class ActivityPlanAccrualController {

    @Autowired
    private ActivityPlanAccrualService service;

    @Autowired
    private InstitutionActivityService institutionActivityService;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private ActivityPlanRepository activityPlanRepository;

    @Autowired
    private DocentRepository docentRepository;

    @Autowired
    private PlanService planService;

    @Autowired
    private UtilCommonsService utilCommonsService;

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody Converter converter, BindingResult result) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        ActivityInstitutionJoin activityInstitutionJoin = new ActivityInstitutionJoin(converter);
        Optional<Docent> docent = docentRepository.findByIdPerson(activityInstitutionJoin.getIdPerson());
        if (docent.isPresent()) {
            Plan plan = new Plan();
            plan.setPeriod(activityInstitutionJoin.getPeriod());
            plan.setIdDocent(docent.get().getIdDocent());
            plan.setStarDate(LocalDate.now());
            plan = (Plan) planService.save(plan).getBody();
            if (plan != null) {
                activityInstitutionJoin.getActivityPlanAccrual().setIdPlan(plan.getIdPlan());
                ResponseEntity<?> responseEntity = service.save(activityInstitutionJoin.getActivityPlanAccrual());
                ActivityPlan activityPlan = (ActivityPlan) responseEntity.getBody();
                if (activityPlan == null) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al cargar el plan activity");
                }
                activityInstitutionJoin.getInstitutionActivity().setIdActivity(activityPlan.getActivity().getIdActivity());
                institutionActivityService.save(activityInstitutionJoin.getInstitutionActivity());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityPlan);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Problemas al cargar el plan");
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El id no pertenece a ningun docente");
    }

    @DeleteMapping("/{idActivityPlan}")
    public ResponseEntity<?> deleteByIdActivityPlan(@PathVariable Long idActivityPlan) {
        Optional<ActivityPlan> activityPlan = activityPlanRepository.findById(idActivityPlan);
        if (activityPlan.isPresent()) {
            service.deleteByIdActivityPlan(idActivityPlan);
            Optional<Institution> institution = institutionRepository.findInstitutionByIdActivity(activityPlan.get().getActivity().getIdActivity());
            institution.ifPresent(value -> institutionActivityService.deleteById(value.getIdInstitution()));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al eliminar");
    }

    @PutMapping("/{idActivityPlan}")
    public ResponseEntity<?> update(@Valid @RequestBody Converter converter, BindingResult result,
                                    @PathVariable Long idActivityPlan) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        ActivityInstitutionJoin activityInstitutionJoin = new ActivityInstitutionJoin(converter);
        Optional<Docent> docent = docentRepository.findByIdPerson(activityInstitutionJoin.getIdPerson());
        if (docent.isPresent()) {
            Optional<ActivityPlan> activityPlan = activityPlanRepository.findById(idActivityPlan);
            if (activityPlan.isPresent()) {
                activityInstitutionJoin.getActivityPlanAccrual().setIdPlan(activityPlan.get().getIdPlan());
                ResponseEntity<?> responseEntity = service.update(activityInstitutionJoin.getActivityPlanAccrual(), idActivityPlan);
                activityInstitutionJoin.getInstitutionActivity().setIdActivity(activityPlan.get().getActivity().getIdActivity());
                Optional<Institution> institution = institutionRepository.findInstitutionByIdActivity(activityPlan.get().getActivity().getIdActivity());
                institution.ifPresent(value -> institutionActivityService.update(activityInstitutionJoin.getInstitutionActivity(), value.getIdInstitution()));
                return responseEntity;
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al actualizar");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Id de persona no encontrado");
    }

}
