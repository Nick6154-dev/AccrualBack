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
    ResponseEntity<?> save(@RequestBody PlanInstitutionActivity planInstitutionActivity) {
        HashMap<String, String> data = new HashMap<>();
        data.put("response", planInstitutionActivityService.addNewActivityWithInstitution(planInstitutionActivity));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
    }

    @DeleteMapping("/{idActivityPlan}")
    public ResponseEntity<?> deleteByIdActivityPlan(@PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(planInstitutionActivityService.deleteActivityWithInstitution(idActivityPlan));
    }

    @PutMapping("/{idActivityPlan}")
    public ResponseEntity<?> update(@RequestBody PlanInstitutionActivity planInstitutionActivity,
                                    @PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(planInstitutionActivityService.updateActivityWithInstitution(planInstitutionActivity, idActivityPlan));
    }

    @PatchMapping("/validateActivity/{idActivityPlan}")
    public ResponseEntity<?> validateActivities(@RequestBody PlanInstitutionActivity planInstitutionActivity,
                                                @PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(planInstitutionActivityService.validateActivitiesByIdActivityPlan(planInstitutionActivity, idActivityPlan));
    }

}
