package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@RestController
public class TipoActividadRest {

    @Autowired
    private ITipoActividadService service;

    @GetMapping({"/tipoActividad/listAll", "/tipoActividad/listAll/"})
    public List<TipoActividad> findAll() {
        return service.findAll();
    }

    @GetMapping("/tipoActividad/findById/{id}")
    public TipoActividad findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/tipoActividad/save", "/tipoActividad/save/"})
    public void save(@RequestBody TipoActividad tipoActividad) {
        service.save(tipoActividad);
    }

    @DeleteMapping("/tipoActividad/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/tipoActividad/update/{id}")
    public void update(@PathVariable Long id, @RequestBody TipoActividad tipoActividad) {
        service.update(id, tipoActividad);
    }

}
