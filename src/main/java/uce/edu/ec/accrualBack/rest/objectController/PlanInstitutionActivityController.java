package uce.edu.ec.accrualBack.rest.objectController;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;

import java.util.*;

@RestController
@RequestMapping("/activityPlanAccrual")
public class PlanInstitutionActivityController {

    @Autowired
    private PlanInstitutionActivityService planInstitutionActivityService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @GetMapping("/{idActivityPlan}")
    public ResponseEntity<?> findById(@PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityPlanService.findById(idActivityPlan));
    }

    @SneakyThrows
    @GetMapping("/byPlan/{idPerson},{idPeriod}")
    public ResponseEntity<?> findActivitiesPlanByPlan(@PathVariable Long idPerson, @PathVariable Long idPeriod) {
        Map<Integer, Object> response = planInstitutionActivityService.findActivitiesPlanByPlan(idPerson, idPeriod);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping
    ResponseEntity<?> save(@RequestBody PlanInstitutionActivity planInstitutionActivity) {
        Map<Integer, String> response = planInstitutionActivityService.addNewActivityWithInstitution(planInstitutionActivity);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{idActivityPlan}")
    public ResponseEntity<?> deleteByIdActivityPlan(@PathVariable Long idActivityPlan) {
        Map<Integer, String> response = planInstitutionActivityService.deleteActivityWithInstitution(idActivityPlan);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PutMapping("/{idActivityPlan}")
    public ResponseEntity<?> update(@RequestBody PlanInstitutionActivity planInstitutionActivity,
                                    @PathVariable Long idActivityPlan) {
        Map<Integer, String> response = planInstitutionActivityService.updateActivityWithInstitution(planInstitutionActivity, idActivityPlan);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/validateActivity/{idActivityPlan}")
    public ResponseEntity<?> validateActivities(@RequestBody PlanInstitutionActivity planInstitutionActivity,
                                                @PathVariable Long idActivityPlan) {
        Map<Integer, String> response = planInstitutionActivityService.validateActivitiesByIdActivityPlan(planInstitutionActivity, idActivityPlan);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
