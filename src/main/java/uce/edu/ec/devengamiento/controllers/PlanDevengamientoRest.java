package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;
import uce.edu.ec.devengamiento.models.service.IPlanDevengamientoService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/planDevengamiento")
@CrossOrigin(origins = "${enviroments.linkFrontEnd}")
public class PlanDevengamientoRest {

    @Autowired
    private IPlanDevengamientoService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<PlanDevengamiento> listAll() {
        return service.findAll();
    }

    @GetMapping("/findByIdDocente/{idDocente}")
    public List<PlanDevengamiento> listByIdDocente(@PathVariable Long idDocente) {
        return service.findByIdDocente(idDocente);
    }

    @GetMapping("/findById/{id}")
    public PlanDevengamiento listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save/{idDocente}")
    public void save(@PathVariable Long idDocente, @RequestBody PlanDevengamiento planDevengamiento) {
        service.save(idDocente, planDevengamiento);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody PlanDevengamiento planDevengamiento) {
        service.update(id, planDevengamiento);
    }

}
