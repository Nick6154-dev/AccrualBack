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
import uce.edu.ec.accrual.models.repository.ActivityPlanRepository;
import uce.edu.ec.accrual.models.repository.InstitutionRepository;
import uce.edu.ec.accrual.models.repository.OtherInstitutionRepository;
import uce.edu.ec.accrual.models.repository.UniversityInstitutionRepository;
import uce.edu.ec.accrual.models.service.InstitutionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService service;

    @Autowired
    private InstitutionRepository repository;

    @Autowired
    private OtherInstitutionRepository otherInstitutionRepository;

    @Autowired
    private UniversityInstitutionRepository universityInstitutionRepository;

    @Autowired
    private ActivityPlanRepository activityPlanRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idInstitution}")
    public ResponseEntity<?> findById(@PathVariable Long idInstitution) {
        return service.findById(idInstitution);
    }

    @GetMapping("/withDetails/{idInstitution}")
    public ResponseEntity<?> findWithDetails(@PathVariable Long idInstitution) {
        return repository.findById(idInstitution).map(value -> {
            Optional<OtherInstitution> otherInstitution = otherInstitutionRepository
                    .findOtherInstitutionByInstitution(value);
            UniversityInstitution universityInstitution = universityInstitutionRepository
                    .findUniversityInstitutionByInstitution(value).orElse(new UniversityInstitution());
            if (otherInstitution.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(otherInstitution);
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(universityInstitution);
            }
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("El id especificado no se encuentra en el sistema"));
    }

    @GetMapping("/withDetailsByIdPlan/{idPlan}")
    public ResponseEntity<?> findWithDetailsByPlan(@PathVariable Long idPlan) {
        Optional<List<ActivityPlan>> activityPlan = activityPlanRepository.findActivityPlansByIdPlan(idPlan);
        if (activityPlan.isPresent()) {
            Long idActivity = activityPlan.get().get(0).getActivity().getIdActivity();
            Optional<Institution> institution = repository.findInstitutionByIdActivity(idActivity);
            if (institution.isPresent()) {
                Optional<OtherInstitution> otherInstitution = otherInstitutionRepository
                        .findOtherInstitutionByInstitution(institution.get());
                UniversityInstitution universityInstitution = universityInstitutionRepository
                        .findUniversityInstitutionByInstitution(institution.get()).orElse(new UniversityInstitution());
                if (otherInstitution.isPresent()) {
                    return ResponseEntity.status(HttpStatus.FOUND).body(otherInstitution);
                } else {
                    return ResponseEntity.status(HttpStatus.FOUND).body(universityInstitution);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al encontrar la institucion");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Problemas al encontrar el plan de la actividad");
        }
    }

}
