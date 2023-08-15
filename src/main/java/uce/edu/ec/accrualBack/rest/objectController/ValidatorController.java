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
import java.util.Map;

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

    @PatchMapping("/approvePlan")
    public ResponseEntity<?> validatePlanByPerson(@RequestBody Map<String, String> newValues) {
        Map<Integer, String> response = validatorService.validatePlanByPerson(newValues);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/approveAllPlans")
    public ResponseEntity<?> approveAllPlans() {
        Map<Integer, String> response = validatorService.approveAllPlans();
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
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

    @PostMapping("/generateDocentInformationExcel/{idPerson}")
    public ResponseEntity<?> generateDocentInformationExcel(@PathVariable Long idPerson) {
        byte[] excelBytes = validatorService.generateExcelDocentData(idPerson);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        Person personInformation = personService.findById(idPerson);
        String fullNames = personInformation.getName() + " " + personInformation.getLastname();
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
    public ResponseEntity<?> generateExcelSelectDocentsActivitiesPlan(@RequestBody List<Long> idsPeople) {
        byte[] excelBytes = validatorService.generateExcelSelectDocentsActivitiesPlan(idsPeople);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Actividades de Docentes.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

}
