package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.service.UniversityInstitutionService;

@RestController
@RequestMapping("/universityInstitution")
public class UniversityInstitutionController {

    @Autowired
    private UniversityInstitutionService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

}
