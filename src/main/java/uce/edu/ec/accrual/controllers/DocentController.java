package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.service.DocentService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/docent")
public class DocentController {

    @Autowired
    private DocentService service;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idDocent}")
    public ResponseEntity<?> findById(@PathVariable Long idDocent) {
        return service.findById(idDocent);
    }

    @GetMapping("/byIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        return service.findByIdPerson(idPerson);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Docent docent, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.save(docent);
    }

    @DeleteMapping("/{idDocent}")
    public ResponseEntity<?> deleteById(@PathVariable Long idDocent) {
        return service.deleteById(idDocent);
    }

    @PutMapping("/{idDocent}")
    public ResponseEntity<?> update(@Valid @RequestBody Docent docent, BindingResult result, @PathVariable Long idDocent) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.updateAll(docent, idDocent);
    }

}
