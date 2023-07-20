package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodService;

import java.util.List;

@RestController
@RequestMapping("/period")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.findAll());
    }

    @GetMapping("/findAllWithDetails")
    public ResponseEntity<?> findAllWithDetails() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.findAllWithDetails());
    }

    @GetMapping("/findAllActivePeriods")
    public ResponseEntity<?> findAllActivePeriods() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.findActivePeriodTrue());
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePeriod(@RequestBody Period period) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.save(period));
    }

    @PatchMapping("/switchActivePeriod/{idPeriod}")
    public ResponseEntity<?> switchActivePeriod(@PathVariable Long idPeriod) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.switchActivePeriod(idPeriod));
    }

    @PatchMapping("/switchStatePeriod/{idPeriod},{state}")
    public ResponseEntity<?> switchStatePeriod(@PathVariable Long idPeriod, @PathVariable Integer state,
                                               @RequestBody List<Long> docents) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.switchStatePeriod(idPeriod, state, docents));
    }

    @DeleteMapping("/deletePeriodById/{idPeriod}")
    public ResponseEntity<?> deletePeriodById(@PathVariable Long idPeriod) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.delete(periodService.findById(idPeriod)));
    }

}
