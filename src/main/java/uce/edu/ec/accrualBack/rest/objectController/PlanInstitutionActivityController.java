package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.object.Converter;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/activityPlanAccrual")
public class PlanInstitutionActivityController {

    @Autowired
    private UtilCommonsService utilCommonsService;

    @Autowired
    private PlanInstitutionActivityService planInstitutionActivityService;

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody Converter converter, BindingResult result) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(planInstitutionActivityService.addNewActivityWithInstitution(converter));
    }

    @DeleteMapping("/{idActivityPlan}")
    public ResponseEntity<?> deleteByIdActivityPlan(@PathVariable Long idActivityPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(planInstitutionActivityService.deleteActivityWithInstitution(idActivityPlan));
    }

    @PutMapping("/{idActivityPlan}")
    public ResponseEntity<?> update(@Valid @RequestBody Converter converter, BindingResult result,
                                    @PathVariable Long idActivityPlan) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(planInstitutionActivityService.updateActivityWithInstitution(converter, idActivityPlan));
    }


}
