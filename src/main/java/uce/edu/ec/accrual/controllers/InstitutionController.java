package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.entity.OtherInstitution;
import uce.edu.ec.accrual.models.entity.UniversityInstitution;
import uce.edu.ec.accrual.models.repository.InstitutionRepository;
import uce.edu.ec.accrual.models.repository.OtherInstitutionRepository;
import uce.edu.ec.accrual.models.repository.UniversityInstitutionRepository;
import uce.edu.ec.accrual.models.service.InstitutionService;

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
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id especificado no se encuentra en el sistema"));
    }

}
