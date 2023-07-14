package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.service.entityService.interfaces.AccrualDataService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/accrualData")
public class AccrualDataController {

    @Autowired
    private AccrualDataService service;

    @Autowired
    private DocentService docentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idAccrualData}")
    public ResponseEntity<?> findById(@PathVariable Long idAccrualData) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idAccrualData));
    }

    @GetMapping("/byIdDocent/{idDocent}")
    public ResponseEntity<?> findByDocent(@PathVariable Long idDocent) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByDocent(docentService.findById(idDocent)));
    }

    @GetMapping("/ByIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getCategory() != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByDocent(docent));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Id persona no encontrada: " + idPerson);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AccrualData accrualData, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(accrualData));
    }

    @PatchMapping("/approveSettlement/{idPerson}")
    public void approveSettlement(@PathVariable Long idPerson) {
        mailService.sendSettlementNotificationMail(idPerson);
    }

    @DeleteMapping("/{idAccrualData}")
    public ResponseEntity<?> delete(@PathVariable Long idAccrualData) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idAccrualData));
    }

    @PutMapping("/{idAccrualData}")
    public ResponseEntity<?> update(@Valid @RequestBody AccrualData accrualData, BindingResult result, @PathVariable Long idAccrualData) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(accrualData, idAccrualData));
    }

    @PatchMapping("/observation/{idAccrualData}")
    public ResponseEntity<?> saveObservation(@Valid @RequestBody String observations, BindingResult result, @PathVariable Long idAccrualData) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateObservations(observations, idAccrualData));
    }

}
