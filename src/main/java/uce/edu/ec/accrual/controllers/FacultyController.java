package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.repository.UniversityRepository;
import uce.edu.ec.accrual.models.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return facultyService.findAll();
    }

    @GetMapping("/{idFaculty}")
    public ResponseEntity<?> findById(@PathVariable Long idFaculty) {
        return facultyService.findById(idFaculty);
    }

    @GetMapping("/{idUniversity}")
    public ResponseEntity<?> findByUniversity(@PathVariable Long idUniversity) {
        return universityRepository.findById(idUniversity).map(value -> ResponseEntity.status(HttpStatus.FOUND)
                        .body(facultyService.findFacultiesByUniversity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
