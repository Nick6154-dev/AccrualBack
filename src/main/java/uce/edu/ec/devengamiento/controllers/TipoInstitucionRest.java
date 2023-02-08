package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.TipoInstitucion;
import uce.edu.ec.devengamiento.models.service.ITipoInstitucionService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/tipoInstitucion")
public class TipoInstitucionRest {

    @Autowired
    private ITipoInstitucionService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<TipoInstitucion> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public TipoInstitucion findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody TipoInstitucion tipoInstitucion) {
        service.save(tipoInstitucion);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody TipoInstitucion tipoInstitucion) {
        service.update(id, tipoInstitucion);
    }

}
