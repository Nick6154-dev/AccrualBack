package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;
import uce.edu.ec.accrual.models.service.DocentService;
import uce.edu.ec.accrual.models.service.NetworkService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/network")
public class NetworkController {

    @Autowired
    private NetworkService service;

    @Autowired
    private UtilCommonsService commonsService;

    @Autowired
    private DocentService docentService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idNetwork}")
    public ResponseEntity<?> findById(@PathVariable Long idNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idNetwork));
    }

    @GetMapping("/byIdDocent/{idDocent}")
    public ResponseEntity<?> findByDocent(@PathVariable Long idDocent) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByDocent(docentService.findById(idDocent)));
    }

    @GetMapping("/byIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getCategory() != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByDocent(docent));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Id persona no encontrada : " + idPerson);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Network network, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(network));
    }

    @DeleteMapping("/{idNetwork}")
    public ResponseEntity<?> deleteById(@PathVariable Long idNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idNetwork));
    }

    @PutMapping("/{idNetwork}")
    public ResponseEntity<?> update(@Valid @RequestBody Network network, BindingResult result, @PathVariable Long idNetwork) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(network, idNetwork));
    }

}
