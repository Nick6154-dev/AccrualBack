package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.service.PlanService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService service;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idPlan}")
    public ResponseEntity<?> findById(@PathVariable Long idPlan) {
        return service.findById(idPlan);
    }

    @GetMapping("/byIdPersonPeriod/{idPerson},{period}")
    public ResponseEntity<?> findByPersonPeriod(@PathVariable Long idPerson, @PathVariable String period) {
        return service.findByIdPersonAndPeriod(idPerson, period);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Plan plan, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.save(plan);
    }

    @DeleteMapping("/{idPlan}")
    public ResponseEntity<?> deleteById(@PathVariable Long idPlan) {
        return service.deleteById(idPlan);
    }

    @PutMapping("/{idPlan}")
    public ResponseEntity<?> update(@Valid @RequestBody Plan plan, BindingResult result, @PathVariable Long idPlan) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.update(plan, idPlan);
    }

    @PatchMapping("/updatePlanNotEditable/{idPerson},{period}")
    public ResponseEntity<?> updatePlanNotEditable(@PathVariable Long idPerson, @PathVariable String period) {
        return service.updateNotEditable(idPerson, period);
    }

}

