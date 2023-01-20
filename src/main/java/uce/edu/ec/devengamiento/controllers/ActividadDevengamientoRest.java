package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;
import uce.edu.ec.devengamiento.models.service.IActividadDevengamietoService;

import java.util.List;

@RestController
public class ActividadDevengamientoRest {

    @Autowired
    private IActividadDevengamietoService service;

    @GetMapping({"/actividadDevengamiento/listAll", "/actividadDevengamiento/listAll/"})
    public List<ActividadDevengamiento> listAll() {
        return service.findAll();
    }

    @GetMapping("/actividadDevengamiento/listById/{id}")
    public ActividadDevengamiento listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/actividadDevengamiento/save", "/actividadDevengamiento/save/"})
    public void save(@RequestBody ActividadDevengamiento actividadDevengamiento) {
        service.save(actividadDevengamiento);
    }

    @DeleteMapping("/actividadDevengamiento/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/actividadDevengamiento/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActividadDevengamiento actividadDevengamiento) {
        service.update(id, actividadDevengamiento);
    }

}
