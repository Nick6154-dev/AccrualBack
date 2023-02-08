package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.DetalleDocente;
import uce.edu.ec.devengamiento.models.service.IDetalleDocenteService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/detalleDocente")
public class DetalleDocenteRest {

    @Autowired
    private IDetalleDocenteService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<DetalleDocente> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public DetalleDocente findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody DetalleDocente detalleDocente) {
        service.save(detalleDocente);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody DetalleDocente detalleDocente) {
        service.update(id, detalleDocente);
    }

}
