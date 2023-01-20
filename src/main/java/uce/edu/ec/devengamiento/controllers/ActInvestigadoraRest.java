package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActInvestigadora;
import uce.edu.ec.devengamiento.models.service.IActInvestigadoraService;

import java.util.List;

@RestController
public class ActInvestigadoraRest {

    @Autowired
    private IActInvestigadoraService service;

    @GetMapping({"/actInvestigadora/listAll", "/actInvestigadora/listAll/"})
    public List<ActInvestigadora> findAll() {
        return service.findAll();
    }

    @GetMapping("/actInvestigadora/findById/{id}")
    public ActInvestigadora findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/actInvestigadora/save", "/actInvestigadora/save/"})
    public void save(@RequestBody ActInvestigadora actInvestigadora) {
        service.save(actInvestigadora);
    }

    @DeleteMapping("/actInvestigadora/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/actInvestigadora/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActInvestigadora actInvestigadora) {
        service.update(id, actInvestigadora);
    }

}
