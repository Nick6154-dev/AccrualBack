package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/tipoActividad")
public class TipoActividadRest {

    @Autowired
    private ITipoActividadService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<TipoActividad> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public TipoActividad findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody TipoActividad tipoActividad) {
        service.save(tipoActividad);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody TipoActividad tipoActividad) {
        service.update(id, tipoActividad);
    }

}
