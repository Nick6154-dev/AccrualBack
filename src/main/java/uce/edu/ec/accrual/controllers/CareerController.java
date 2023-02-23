package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.repository.FacultyRepository;
import uce.edu.ec.accrual.models.service.CareerService;

@RestController
@RequestMapping("/career")
public class CareerController {

    @Autowired
    private CareerService careerService;

    @Autowired
    private FacultyRepository facultyRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return careerService.findAll();
    }

    @GetMapping("/{idCareer}")
    public ResponseEntity<?> findById(@PathVariable Long idCareer) {
        return careerService.findById(idCareer);
    }

    @GetMapping("/byIdCareer/{idFaculty}")
    public ResponseEntity<?> findByFaculty(@PathVariable Long idFaculty) {
        return facultyRepository.findById(idFaculty).map(value -> ResponseEntity
                        .status(HttpStatus.FOUND).body(careerService.findCareersByFaculty(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
