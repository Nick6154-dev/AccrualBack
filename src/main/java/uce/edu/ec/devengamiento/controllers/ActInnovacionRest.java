package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActInnovacion;
import uce.edu.ec.devengamiento.models.service.IActInnovacionService;

import java.util.List;

@RestController
public class ActInnovacionRest {

    @Autowired
    private IActInnovacionService service;

    @GetMapping({"/actInnovacion/listAll", "/actInnovacion/listAll/"})
    public List<ActInnovacion> findAll() {
        return service.findAll();
    }

    @GetMapping("/actInnovacion/findById/{id}")
    public ActInnovacion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/actInnovacion/save", "/actInnovacion/save/"})
    public void save(@RequestBody ActInnovacion actInnovacion) {
        service.save(actInnovacion);
    }

    @DeleteMapping("/actInnovacion/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/actInnovacion/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActInnovacion actInnovacion) {
        service.update(id, actInnovacion);
    }

}
