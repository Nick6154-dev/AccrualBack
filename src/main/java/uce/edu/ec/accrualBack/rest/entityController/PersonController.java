package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PersonService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idPerson}")
    public ResponseEntity<?> findById(@PathVariable Long idPerson) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idPerson));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(person));
    }

    @DeleteMapping("/{idPerson}")
    public ResponseEntity<?> deleteById(@PathVariable Long idPerson) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idPerson));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(person));
    }

    @PutMapping("/{idPerson}")
    public ResponseEntity<?> update(@Valid @RequestBody Person person, BindingResult result, @PathVariable Long idPerson) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(person, idPerson));
    }

}
