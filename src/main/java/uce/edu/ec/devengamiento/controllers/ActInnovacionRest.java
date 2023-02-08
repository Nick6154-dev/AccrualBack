package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActInnovacion;
import uce.edu.ec.devengamiento.models.service.IActInnovacionService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/actInnovacion")
public class ActInnovacionRest {

    @Autowired
    private IActInnovacionService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<ActInnovacion> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ActInnovacion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody ActInnovacion actInnovacion) {
        service.save(actInnovacion);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActInnovacion actInnovacion) {
        service.update(id, actInnovacion);
    }

}
