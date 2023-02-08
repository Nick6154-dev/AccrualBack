package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.UniversidadInstitucion;
import uce.edu.ec.devengamiento.models.service.IUniversidadInstitucionService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/universidadInstitucion")
public class UniversidadInstitucionRest {

    @Autowired
    private IUniversidadInstitucionService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<UniversidadInstitucion> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public UniversidadInstitucion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody UniversidadInstitucion universidadInstitucion) {
        service.save(universidadInstitucion);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody UniversidadInstitucion universidadInstitucion) {
        service.update(id, universidadInstitucion);
    }

}
