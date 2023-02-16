package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;
import uce.edu.ec.devengamiento.models.objects.OActividad;
import uce.edu.ec.devengamiento.models.service.IActividadDevengamietoService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/actividadDevengamiento")
public class ActividadDevengamientoRest {

    @Autowired
    private IActividadDevengamietoService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<ActividadDevengamiento> listAll() {
        return service.findAll();
    }

    @GetMapping("/findAllByIdPlan/{idPlanDevengamiento}")
    public List<ActividadDevengamiento> listAllByIdPlan(@PathVariable Long idPlanDevengamiento) {
        return service.findAllByIdPlanDevengamiento(idPlanDevengamiento);
    }

    @GetMapping("/findById/{id}")
    public ActividadDevengamiento listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public ActividadDevengamiento save(@RequestBody OActividad oActividad) {
        return service.save(oActividad);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActividadDevengamiento actividadDevengamiento) {
        service.update(id, actividadDevengamiento);
    }

}
