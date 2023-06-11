package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Period;
import uce.edu.ec.accrual.models.service.PeriodService;

@RestController
@RequestMapping("/period")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePeriod(@RequestBody Period period) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.save(period));
    }

    @PatchMapping("/switchActivePeriod/{idPeriod}")
    public ResponseEntity<?> switchActivePeriod(@PathVariable Long idPeriod) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.switchActivePeriod(idPeriod));
    }

}