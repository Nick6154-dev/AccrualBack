package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;
import uce.edu.ec.devengamiento.models.service.IDatosDevengamientoService;

import java.util.List;

@RestController
public class DatosDevengamientoRest {

    @Autowired
    private IDatosDevengamientoService service;

    @GetMapping({"/datosDevengamiento/listALl", "/datosDevengamiento/listAll/"})
    public List<DatosDevengamiento> listAll() {
        return service.findAll();
    }

    @GetMapping("/datosDevengamiento/listById/{id}")
    public DatosDevengamiento listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/datosDevengamiento/save", "/datosDevengamiento/save/"})
    public void save(@RequestBody DatosDevengamiento datosDevengamiento) {
        service.save(datosDevengamiento);
    }

    @DeleteMapping("/datosDevengamiento/deleteById/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/datosDevengamiento/update/{id}")
    public void update(@PathVariable Long id, @RequestBody DatosDevengamiento datosDevengamiento) {
        service.update(id, datosDevengamiento);
    }

}
