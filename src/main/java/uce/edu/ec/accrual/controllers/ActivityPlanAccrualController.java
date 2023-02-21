package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.object.ActivityPlanAccrual;
import uce.edu.ec.accrual.models.service.ActivityPlanAccrualService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/activityPlan")
public class ActivityPlanAccrualController {

    @Autowired
    private ActivityPlanAccrualService service;

    @Autowired
    private UtilCommonsService utilCommonsService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ActivityPlanAccrual activityPlanAccrual, BindingResult result) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        return service.save(activityPlanAccrual);
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
