package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodService;

import java.util.Map;

@RestController
@RequestMapping("/period")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.findAll());
    }

    @GetMapping("/findAllByIdPerson/{idPerson}")
    public ResponseEntity<?> findAllByIdPerson(@PathVariable Long idPerson) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.findAllByIdPerson(idPerson));
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
        Map<Integer, String> response = periodService.save(period);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/switchActivePeriod/{idPeriod}")
    public ResponseEntity<?> switchActivePeriod(@PathVariable Long idPeriod) {
        Map<Integer, String> response = periodService.switchActivePeriod(idPeriod);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PatchMapping("/switchStatePeriods")
    public ResponseEntity<?> switchStatePeriods(@RequestBody Map<String, Object> objects) {
        Map<Integer, String> response = periodService.switchStatePeriods(objects);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/deletePeriodById/{idPeriod}")
    public ResponseEntity<?> deletePeriodById(@PathVariable Long idPeriod) {
        Map<Integer, String> response = periodService.deleteById(idPeriod);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
