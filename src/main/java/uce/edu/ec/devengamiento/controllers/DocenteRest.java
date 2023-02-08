package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.service.IDocenteService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/docente")
public class DocenteRest {

    @Autowired
    private IDocenteService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<Docente> listAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public Docente listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody Docente docente) {
        service.save(docente);
    }

    @DeleteMapping({"/deleteById/{id}"})
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Docente docente) {
        service.update(id, docente);
    }

}
