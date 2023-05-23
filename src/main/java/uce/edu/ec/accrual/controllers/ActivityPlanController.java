package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.OtherInstitution;
import uce.edu.ec.accrual.models.entity.UniversityInstitution;
import uce.edu.ec.accrual.models.object.ActivityInstitutionShow;
import uce.edu.ec.accrual.models.repository.InstitutionRepository;
import uce.edu.ec.accrual.models.repository.OtherInstitutionRepository;
import uce.edu.ec.accrual.models.repository.UniversityInstitutionRepository;
import uce.edu.ec.accrual.models.service.ActivityPlanService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activityPlan")
public class ActivityPlanController {

    @Autowired
    private ActivityPlanService service;

    @Autowired
    private InstitutionRepository repository;

    @Autowired
    private OtherInstitutionRepository otherInstitutionRepository;

    @Autowired
    private UniversityInstitutionRepository universityInstitutionRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idActivityPlan}")
    public ResponseEntity<?> findById(@PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idActivityPlan));
    }

    @GetMapping("/byPlan/{idPlan}")
    public ResponseEntity<?> findActivityPlanByPlan(@PathVariable Long idPlan) {
        List<ActivityPlan> activityPlans = service.findActivityPlansByIdPlan(idPlan);
        List<ActivityInstitutionShow> activityInstitutionShows = new ArrayList<>();
        assert activityPlans != null;
        for (ActivityPlan ap : activityPlans) {
            Long idActivity = ap.getActivity().getIdActivity();
            Optional<Institution> institution = repository.findInstitutionByIdActivity(idActivity);
            if (institution.isPresent()) {
                Optional<OtherInstitution> otherInstitution = otherInstitutionRepository
                        .findOtherInstitutionByInstitution(institution.get());
                UniversityInstitution universityInstitution = universityInstitutionRepository
                        .findUniversityInstitutionByInstitution(institution.get()).orElse(new UniversityInstitution());
                if (otherInstitution.isPresent()) {
                    activityInstitutionShows.add(new ActivityInstitutionShow(ap, otherInstitution.get()));
                } else {
                    activityInstitutionShows.add(new ActivityInstitutionShow(ap, universityInstitution));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(activityInstitutionShows);
    }

}
