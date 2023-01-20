package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.TipoInstitucion;
import uce.edu.ec.devengamiento.models.service.ITipoInstitucionService;

import java.util.List;

@RestController
public class TipoInstitucionRest {

    @Autowired
    private ITipoInstitucionService service;

    @GetMapping({"/tipoInstitucion/listAll", "/tipoInstitucion/listAll/"})
    public List<TipoInstitucion> findAll() {
        return service.findAll();
    }

    @GetMapping("/tipoInstitucion/findById/{id}")
    public TipoInstitucion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/tipoInstitucion/save", "/tipoInstitucion/save/"})
    public void save(@RequestBody TipoInstitucion tipoInstitucion) {
        service.save(tipoInstitucion);
    }

    @DeleteMapping("/tipoInstitucion/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/tipoInstitucion/update/{id}")
    public void update(@PathVariable Long id, @RequestBody TipoInstitucion tipoInstitucion) {
        service.update(id, tipoInstitucion);
    }

}
