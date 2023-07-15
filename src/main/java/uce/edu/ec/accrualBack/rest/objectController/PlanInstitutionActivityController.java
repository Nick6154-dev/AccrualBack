package uce.edu.ec.accrualBack.rest.objectController;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;

import java.util.*;

@RestController
@RequestMapping("/activityPlanAccrual")
public class PlanInstitutionActivityController {

    @Autowired
    private PlanService planService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private PlanInstitutionActivityService planInstitutionActivityService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private OtherInstitutionService otherInstitutionService;

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @GetMapping("/{idActivityPlan}")
    public ResponseEntity<?> findById(@PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityPlanService.findById(idActivityPlan));
    }

    @SneakyThrows
    @GetMapping("/byPlan/{idPerson},{idPeriod}")
    public ResponseEntity<?> findActivitiesPlanByPlan(@PathVariable Long idPerson, @PathVariable Long idPeriod) {
        Map<Integer, Object> response = planInstitutionActivityService.findActivitiesPlanByPlan(idPerson, idPeriod);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.get(400));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.get(200));
    }

    @PostMapping
    ResponseEntity<String> save(@RequestBody PlanInstitutionActivity planInstitutionActivity) {
        Map<Integer, String> response = planInstitutionActivityService.addNewActivityWithInstitution(planInstitutionActivity);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.get(400));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.get(200));
    }

    @DeleteMapping("/{idActivityPlan}")
    public ResponseEntity<String> deleteByIdActivityPlan(@PathVariable Long idActivityPlan) {
        Map<Integer, String> response = planInstitutionActivityService.deleteActivityWithInstitution(idActivityPlan);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.get(400));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.get(200));
    }

    @PutMapping("/{idActivityPlan}")
    public ResponseEntity<String> update(@RequestBody PlanInstitutionActivity planInstitutionActivity,
                                    @PathVariable Long idActivityPlan) {
        Map<Integer, String> response = planInstitutionActivityService.updateActivityWithInstitution(planInstitutionActivity, idActivityPlan);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.get(400));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.get(200));
    }

    @PatchMapping("/validateActivity/{idActivityPlan}")
    public ResponseEntity<String> validateActivities(@RequestBody PlanInstitutionActivity planInstitutionActivity,
                                                @PathVariable Long idActivityPlan) {
        Map<Integer, String> response = planInstitutionActivityService.validateActivitiesByIdActivityPlan(planInstitutionActivity, idActivityPlan);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response.get(400));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.get(200));
    }

}
