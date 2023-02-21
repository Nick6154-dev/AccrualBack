package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.repository.ActivityPlanRepository;
import uce.edu.ec.accrual.models.service.DescriptionService;

@RestController
@RequestMapping("/description")
public class DescriptionController {

    @Autowired
    private DescriptionService service;

    @Autowired
    private ActivityPlanRepository repository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idDescription}")
    public ResponseEntity<?> findById(@PathVariable Long idDescription) {
        return service.findById(idDescription);
    }

    @GetMapping("/byActivityPlan/{idActivityPlan}")
    public ResponseEntity<?> findDescriptionByIdActivityPlan(@PathVariable Long idActivityPlan) {
        if (repository.findById(idActivityPlan).isPresent()) {
            return service.findDescriptionByActivityPlan(repository.findById(idActivityPlan).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id especificado no existe en el sistema");
    }

}
