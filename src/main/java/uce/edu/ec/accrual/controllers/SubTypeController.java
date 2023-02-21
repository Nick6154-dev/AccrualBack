package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrual.models.repository.TypeRepository;
import uce.edu.ec.accrual.models.service.SubtypeService;

@RestController
@RequestMapping("/subtype")
public class SubTypeController {

    @Autowired
    private SubtypeService service;

    @Autowired
    private TypeRepository repository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idSubtype}")
    public ResponseEntity<?> findById(@PathVariable Long idSubtype) {
        return service.findById(idSubtype);
    }

    @GetMapping("/{idType}")
    public ResponseEntity<?> findSubtypeByType(@PathVariable Long idType) {
        if (repository.findById(idType).isPresent()) {
            return service.findSubtypeByType(repository.findById(idType).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id proporcionado no se ha encontrado en el sistema");
    }

}
