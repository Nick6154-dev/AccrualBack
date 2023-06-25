package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UniversityInstitutionService;

@RestController
@RequestMapping("/universityInstitution")
public class UniversityInstitutionController {

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(universityInstitutionService.findAll());
    }

}
