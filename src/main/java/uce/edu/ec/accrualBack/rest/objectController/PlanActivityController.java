package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.OtherInstitution;
import uce.edu.ec.accrualBack.entity.UniversityInstitution;
import uce.edu.ec.accrualBack.object.ActivityInstitutionShow;
import uce.edu.ec.accrualBack.service.entityService.interfaces.ActivityPlanService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.InstitutionService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.OtherInstitutionService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UniversityInstitutionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/activityPlan")
public class PlanActivityController {

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private OtherInstitutionService otherInstitutionService;

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityPlanService.findAll());
    }

    @GetMapping("/{idActivityPlan}")
    public ResponseEntity<?> findById(@PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityPlanService.findById(idActivityPlan));
    }

    @GetMapping("/byPlan/{idPlan}")
    public ResponseEntity<?> findActivityPlanByPlan(@PathVariable Long idPlan) {
        List<ActivityPlan> activityPlans = activityPlanService.findActivityPlansByIdPlan(idPlan);
        List<ActivityInstitutionShow> activityInstitutionShows = new ArrayList<>();
        assert activityPlans != null;
        for (ActivityPlan ap : activityPlans) {
            Long idActivity = ap.getActivity().getIdActivity();
            Institution institution = institutionService.findInstitutionByActivity(idActivity);
            if (institution != null) {
                OtherInstitution otherInstitution = otherInstitutionService
                        .findOtherInstitutionByInstitution(institution);
                UniversityInstitution universityInstitution = universityInstitutionService
                        .findUniversityInstitutionByInstitution(institution);
                if (otherInstitution != null) {
                    activityInstitutionShows.add(new ActivityInstitutionShow(ap, otherInstitution));
                } else {
                    activityInstitutionShows.add(new ActivityInstitutionShow(ap, universityInstitution));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityInstitutionShows);
    }

}
