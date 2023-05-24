package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.service.ValidatorService;

@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    @GetMapping("/findAllDocentPersonPlans")
    public ResponseEntity<?> findAllPersonDocentPlans() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(validatorService.findAllPersonDocentPlan());
    }

    @GetMapping("/findPlansByPerson/{idPerson}")
    public ResponseEntity<?> findPlansByPerson(@PathVariable Long idPerson) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(validatorService.findPlansByPerson(idPerson));
    }

    @PatchMapping("/updateStateObservationsPlan")
    public ResponseEntity<?> updateStateObservationsPlan(@RequestBody Plan plan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(validatorService.validatePlanByPerson(plan));
    }

    @PostMapping("/generateExcel/{idPerson},{idPlan}")
    public ResponseEntity<?> generateExcel(@PathVariable Long idPerson, @PathVariable Long idPlan) {
        byte[] excelBytes = validatorService.generateExcelActivitiesPlan(idPerson, idPlan);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "ActivitiesPlan.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

}
