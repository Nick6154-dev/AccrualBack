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
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/accrualData")
public class AccrualDataController {

    @Autowired
    private AccrualDataService accrualDataService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.findAll());
    }

    @GetMapping("/findAllPeopleSettlementRequest")
    public ResponseEntity<?> findAllPeopleSettlementRequest() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.findAllPeopleSettlementRequest());
    }

    @GetMapping("/{idAccrualData}")
    public ResponseEntity<?> findById(@PathVariable Long idAccrualData) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.findById(idAccrualData));
    }

    @GetMapping("/byIdDocent/{idDocent}")
    public ResponseEntity<?> findByDocent(@PathVariable Long idDocent) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.findByDocent(docentService.findById(idDocent)));
    }

    @GetMapping("/ByIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getCategory() != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.findByDocent(docent));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Id persona no encontrada: " + idPerson);
        }
    }

    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@RequestBody AccrualData accrualData, @PathVariable Long idPerson) {
        Map<Integer, String> response = accrualDataService.save(accrualData, idPerson);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/requestSettlement/{idPerson}")
    public ResponseEntity<?> requestSettlement(@PathVariable Long idPerson) {
        Map<Integer, String> response = accrualDataService.requestApproval(idPerson);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/approveSettlement/{idPerson},{approved}")
    public ResponseEntity<?> approveSettlement(@PathVariable Long idPerson, @PathVariable boolean approved) {
        Map<Integer, String> response = accrualDataService.approveSettlement(idPerson, approved);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/approveAllSettlementRequest")
    public ResponseEntity<?> approveAllSettlementRequest() {
        Map<Integer, String> response = accrualDataService.approveAllRequestSettlement();
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{idAccrualData}")
    public ResponseEntity<?> delete(@PathVariable Long idAccrualData) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.deleteById(idAccrualData));
    }

    @PutMapping("/update/{idAccrualData}")
    public ResponseEntity<?> update(@RequestBody AccrualData accrualData, @PathVariable Long idAccrualData) {
        Map<Integer, String> response = accrualDataService.update(accrualData, idAccrualData);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/observation/{idAccrualData}")
    public ResponseEntity<?> saveObservation(@Valid @RequestBody String observations, BindingResult result, @PathVariable Long idAccrualData) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accrualDataService.updateObservations(observations, idAccrualData));
    }

}
