package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.DetalleDocente;
import uce.edu.ec.devengamiento.models.service.IDetalleDocenteService;

import java.util.List;

@RestController
public class DetalleDocenteRest {

    @Autowired
    private IDetalleDocenteService service;

    @GetMapping({"/detalleDocente/listAll", "/detalleDocente/listAll/"})
    public List<DetalleDocente> findAll() {
        return service.findAll();
    }

    @GetMapping("/detalleDocente/findById/{id}")
    public DetalleDocente findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/detalleDocente/save", "/detalleDocente/save/"})
    public void save(@RequestBody DetalleDocente detalleDocente) {
        service.save(detalleDocente);
    }

    @DeleteMapping("/detalleDocente/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/detalleDocente/update/{id}")
    public void update(@PathVariable Long id, @RequestBody DetalleDocente detalleDocente) {
        service.update(id, detalleDocente);
    }

}
