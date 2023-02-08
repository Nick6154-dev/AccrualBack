package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Universidad;
import uce.edu.ec.devengamiento.models.service.IUniversidadService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/universidad")
public class UniversidadRest {

    @Autowired
    private IUniversidadService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<Universidad> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public Universidad findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody Universidad universidad) {
        service.save(universidad);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Universidad universidad) {
        service.update(id, universidad);
    }

}
