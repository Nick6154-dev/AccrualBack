package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;
import uce.edu.ec.devengamiento.models.service.IPlanDevengamientoService;

import java.util.List;

@RestController
public class PlanDevengamientoRest {

    @Autowired
    private IPlanDevengamientoService service;

    @GetMapping({"/planDevengamiento/listAll", "/planDevengamiento/listAll/"})
    public List<PlanDevengamiento> listAll() {
        return service.findAll();
    }

    @GetMapping("/planDevengamiento/listByIdDocente/{id}")
    public List<PlanDevengamiento> listByIdDocente(Long id) {
        return service.findByIdDocente(id);
    }

    @GetMapping("/planDevengamiento/listById/{id}")
    public PlanDevengamiento listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/planDevengamiento/save", "/planDevengamiento/save/"})
    public void save(@RequestBody PlanDevengamiento planDevengamiento) {
        service.save(planDevengamiento);
    }

    @DeleteMapping("/planDevengamiento/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/planDevegamieto/update/{id}")
    public void update(@PathVariable Long id, @RequestBody PlanDevengamiento planDevengamiento) {
        service.update(id, planDevengamiento);
    }

}
