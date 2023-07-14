package uce.edu.ec.accrualBack.rest.entityController;

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
import uce.edu.ec.accrualBack.service.entityService.interfaces.ActivityPlanService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.InstitutionService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.OtherInstitutionService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UniversityInstitutionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private OtherInstitutionService otherInstitutionService;

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(institutionService.findAll());
    }

    @GetMapping("/{idInstitution}")
    public ResponseEntity<?> findById(@PathVariable Long idInstitution) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(institutionService.findById(idInstitution));
    }

    @GetMapping("/withDetails/{idInstitution}")
    public ResponseEntity<?> findWithDetails(@PathVariable Long idInstitution) {
        return findByIdInstitution(idInstitution);
    }

    @GetMapping("/withDetailsByIdPlan/{idPlan}")
    public ResponseEntity<?> findWithDetailsByPlan(@PathVariable Long idPlan) {
        List<HashMap<String, Object>> objects = new ArrayList<>();
        List<ActivityPlan> activityPlan = activityPlanService.findActivityPlansByIdPlan(idPlan);
        if (activityPlan != null) {
            for (ActivityPlan ap : activityPlan) {
                Long idActivity = ap.getActivity().getIdActivity();
                Institution institution = institutionService.findInstitutionByActivity(idActivity);
                if (institution != null) {
                    HashMap<String, Object> map = new HashMap<>();
                    OtherInstitution otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(institution);
                    UniversityInstitution universityInstitution = universityInstitutionService.findUniversityInstitutionByInstitution(institution);
                    if (otherInstitution != null) {
                        map.put("other", otherInstitution);
                    } else {
                        map.put("university", universityInstitution);
                    }
                    objects.add(map);
                }
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(objects);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al encontrar el plan de la actividad");
        }
    }

    @GetMapping("/withDetailsByIdActivityPlan/{idActivityPlan}")
    public ResponseEntity<?> findWithDetailsByIdActivityPlan(@PathVariable Long idActivityPlan) {
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan != null) {
            Long idActivity = activityPlan.getActivity().getIdActivity();
            Institution institution = institutionService.findInstitutionByActivity(idActivity);
            if (institution != null) {
                return findByIdInstitution(institution.getIdInstitution());
            }
            return ResponseEntity.status(HttpStatus.FOUND).body("No se encontro una institucion relacionada a ese id");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("No se encontro una actividad plan relacionada a ese id");
    }

    private ResponseEntity<?> findByIdInstitution(Long idInstitution) {
        return Optional.of(institutionService.findById(idInstitution)).map(value -> {
            OtherInstitution otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(value);
            UniversityInstitution universityInstitution = universityInstitutionService.findUniversityInstitutionByInstitution(value);
            if (otherInstitution != null) {
                return ResponseEntity.status(HttpStatus.FOUND).body(otherInstitution);
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(universityInstitution);
            }
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id especificado no se encuentra en el sistema"));
    }

}
