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
    public ResponseEntity<?> findActivityPlanByPlan(@PathVariable Long idPerson, @PathVariable Long idPeriod) {
        Period period = periodService.findById(idPeriod);
        Plan plan = planService.findByIdPersonAndPeriod(idPerson, period);
        List<ActivityPlan> activityPlans = activityPlanService.findActivityPlansByIdPlan(plan.getIdPlan());
        List<Map<String, Object>> activitiesInstitutions = new ArrayList<>();
        assert activityPlans != null;
        for (ActivityPlan ap : activityPlans) {
            Long idActivity = ap.getActivity().getIdActivity();
            Institution institution = institutionService.findInstitutionByActivity(idActivity);
            if (institution.getIdInstitution() != null) {
                OtherInstitution otherInstitution = otherInstitutionService
                        .findOtherInstitutionByInstitution(institution);
                UniversityInstitution universityInstitution = universityInstitutionService
                        .findUniversityInstitutionByInstitution(institution);
                Map<String, Object> activityInstitution = new LinkedHashMap<>();
                activityInstitution.put("activityPlan", ap);
                if (otherInstitution.getIdOther() != null) {
                    activityInstitution.put("institutionPlan", otherInstitution);
                } else {
                    activityInstitution.put("institutionPlan", universityInstitution);
                }
                activitiesInstitutions.add(activityInstitution);
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activitiesInstitutions);
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
