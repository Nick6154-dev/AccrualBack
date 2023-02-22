package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.AccrualData;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.service.AccrualDataService;
import uce.edu.ec.accrual.models.service.DocentService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/accrualData")
public class AccrualDataController {

    @Autowired
    private AccrualDataService service;

    @Autowired
    private DocentService docentService;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idAccrualData}")
    public ResponseEntity<?> findById(@PathVariable Long idAccrualData) {
        return service.findById(idAccrualData);
    }

    @GetMapping("/{idDocent}")
    public ResponseEntity<?> findByDocent(@PathVariable Long idDocent) {
        return service.findByDocent((Docent) docentService.findById(idDocent).getBody());
    }

    @GetMapping("/ByIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        Docent docent = (Docent) docentService.findByIdPerson(idPerson).getBody();
        if (docent.getCategory() != null) {
            return service.findByDocent(docent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Id persona no encontrada: " + idPerson);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AccrualData accrualData, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.save(accrualData);
    }

    @DeleteMapping("/{idAccrualData}")
    public ResponseEntity<?> delete(@PathVariable Long idAccrualData) {
        return service.deleteById(idAccrualData);
    }

    @PostMapping("/{idAccrualData}")
    public ResponseEntity<?> udpate(@Valid @RequestBody AccrualData accrualData, BindingResult result, @PathVariable Long idAccrualData) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.update(accrualData, idAccrualData);
    }

}
