package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;
import uce.edu.ec.devengamiento.models.service.IDatosDevengamientoService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/datosDevengamiento")
public class DatosDevengamientoRest {

    @Autowired
    private IDatosDevengamientoService service;

    @GetMapping({"/findALl", "/findAll/"})
    public List<DatosDevengamiento> listAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public DatosDevengamiento listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody DatosDevengamiento datosDevengamiento) {
        service.save(datosDevengamiento);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody DatosDevengamiento datosDevengamiento) {
        service.update(id, datosDevengamiento);
    }

}
