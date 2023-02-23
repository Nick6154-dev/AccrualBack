package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.object.ActivityPlanAccrual;
import uce.edu.ec.accrual.models.object.InstitutionActivity;
import uce.edu.ec.accrual.models.service.ActivityPlanAccrualService;
import uce.edu.ec.accrual.models.service.InstitutionActivityService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/activityPlanAccrual")
public class ActivityPlanAccrualController {

    @Autowired
    private ActivityPlanAccrualService service;

    @Autowired
    private InstitutionActivityService institutionActivityService;

    @Autowired
    private UtilCommonsService utilCommonsService;

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody ActivityPlanAccrual activityPlanAccrual, BindingResult result1,
                           @Valid @RequestBody InstitutionActivity institutionActivity, BindingResult result2) {
        if (result1.hasErrors()) {
            return utilCommonsService.validate(result1);
        }
        if (result2.hasErrors()) {
            return utilCommonsService.validate(result2);
        }
        return null;
    }

    @DeleteMapping("/{idActivityPlan}")
    public ResponseEntity<?> deleteByIdActivityPlan(@PathVariable Long idActivityPlan) {
        return service.deleteByIdActivityPlan(idActivityPlan);
    }

    @PutMapping("/{idActivityPlan}")
    public ResponseEntity<?> updateByActivityPlan(@Valid @RequestBody ActivityPlanAccrual activityPlanAccrual, BindingResult result,
                                                  @PathVariable Long idActivityPlan) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        return service.update(activityPlanAccrual, idActivityPlan);
    }

}
