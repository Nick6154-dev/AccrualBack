package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Carrera;
import uce.edu.ec.devengamiento.models.service.ICarreraService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/carrera")
public class CarreraRest {

    @Autowired
    private ICarreraService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<Carrera> findAll() {
        return service.findAll();
    }

    @GetMapping("/findAllByIdFacultad/{idFacultad}")
    public List<Carrera> findCarreraByIdFacultad(@PathVariable Long idFacultad) {
        return service.findCarrerasByIdFacultad(idFacultad);
    }

    @GetMapping("/findById/{id}")
    public Carrera findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody Carrera carrera) {
        service.save(carrera);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Carrera carrera) {
        service.update(id, carrera);
    }

}
