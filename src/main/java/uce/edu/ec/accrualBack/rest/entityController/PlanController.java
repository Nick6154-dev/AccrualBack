package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.Plan;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PlanService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService service;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idPlan}")
    public ResponseEntity<?> findById(@PathVariable Long idPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idPlan));
    }

    @GetMapping("/byIdPersonPeriod/{idPerson},{idPeriod}")
    public ResponseEntity<?> findByPersonPeriod(@PathVariable Long idPerson, @PathVariable Long idPeriod) {
        Period period = periodService.findById(idPeriod);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByIdPersonAndPeriod(idPerson, period));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Plan plan, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(plan));
    }

    @DeleteMapping("/{idPlan}")
    public ResponseEntity<?> deleteById(@PathVariable Long idPlan) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idPlan));
    }

    @PutMapping("/{idPlan}")
    public ResponseEntity<?> update(@Valid @RequestBody Plan plan, BindingResult result, @PathVariable Long idPlan) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(plan, idPlan));
    }

    @PatchMapping("/updatePlanNotEditable/{idPerson},{idPeriod}")
    public ResponseEntity<?> updatePlanNotEditable(@PathVariable Long idPerson, @PathVariable Long idPeriod) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateNotEditable(idPerson, idPeriod));
    }

}
