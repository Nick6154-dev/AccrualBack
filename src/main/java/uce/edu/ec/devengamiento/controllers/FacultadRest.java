package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Facultad;
import uce.edu.ec.devengamiento.models.service.IFacultadService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/facultad")
public class FacultadRest {

    @Autowired
    private IFacultadService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<Facultad> findAll() {
        return service.findAll();
    }

    @GetMapping("/findAllByIdUniversidad/{idUniversidad}")
    public List<Facultad> findFacultadsByIdUniversidad(@PathVariable Long idUniversidad) {
        return service.findFacultadsByIdUniversidad(idUniversidad);
    }

    @GetMapping("/findById/{id}")
    public Facultad findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody Facultad facultad) {
        service.save(facultad);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Facultad facultad) {
        service.update(id, facultad);
    }

}
