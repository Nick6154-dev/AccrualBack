package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActEstructuraID;
import uce.edu.ec.devengamiento.models.service.IActEstructuraIDService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/actEstructura")
public class ActEstructuraIDRest {

    @Autowired
    private IActEstructuraIDService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<ActEstructuraID> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ActEstructuraID findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody ActEstructuraID actEstructuraID) {
        service.save(actEstructuraID);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActEstructuraID actEstructuraID) {
        service.update(id, actEstructuraID);
    }

}
