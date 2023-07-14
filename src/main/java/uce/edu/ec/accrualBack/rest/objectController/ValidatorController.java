package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.object.ValidatorObject;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PersonService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.ValidatorService;

import java.util.List;

@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private PersonService personService;

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
        Person person = personService.findById(idPerson);
        String fullNames = person.getName() + " " + person.getLastname();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",  fullNames + ".xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

    @PostMapping("/generateExcelDocentsInPlan")
    public ResponseEntity<?> generateExcelDocentsInPlan() {
        byte[] excelBytes = validatorService.generateExcelDocentsInPlan();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Lista Docentes.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

    @PostMapping("/generateExcelSelectDocentsActivitiesPlan")
    public ResponseEntity<?> generateExcelSelectDocentsActivitiesPlan(@RequestBody List<ValidatorObject> validatorObjects) {
        byte[] excelBytes = validatorService.generateExcelSelectDocentsActivitiesPlan(validatorObjects);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Actividades de Docentes.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

    @PostMapping("/approveAllPlans")
    public ResponseEntity<?> approveAllPlans() {
        validatorService.approveAllPlans();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Todos los planes aprobados");
    }

}
