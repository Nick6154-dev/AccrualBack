package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Person;
import uce.edu.ec.accrual.models.service.PersonService;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idPerson}")
    public ResponseEntity<?> findById(@PathVariable Long idPerson) {
        return service.findById(idPerson);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            return service.validate(result);
        }
        return service.save(person);
    }

    @DeleteMapping("/{idPerson}")
    public ResponseEntity<?> deleteById(@PathVariable Long idPerson) {
        return service.deleteById(idPerson);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            return service.validate(result);
        }
        return service.delete(person);
    }

    @PutMapping("/{idPerson}")
    public ResponseEntity<?> update(@Valid @RequestBody Person person, BindingResult result, @PathVariable Long idPerson) {
        if (result.hasErrors()) {
            return service.validate(result);
        }
        return service.update(person, idPerson);
    }

}
