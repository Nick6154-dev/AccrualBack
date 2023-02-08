package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.OtraInstitucion;
import uce.edu.ec.devengamiento.models.service.IOtraInstitucionService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/otraInstitucion")
public class OtraInstitucionRest {

    @Autowired
    private IOtraInstitucionService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<OtraInstitucion> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public OtraInstitucion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody OtraInstitucion otraInstitucion) {
        service.save(otraInstitucion);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody OtraInstitucion otraInstitucion) {
        service.update(id, otraInstitucion);
    }

}
