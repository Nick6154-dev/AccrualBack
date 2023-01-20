package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.RedSocialInvestigacion;
import uce.edu.ec.devengamiento.models.service.IRedSocialInvestigacionService;

import java.util.List;

@RestController
public class RedSocialInvestigacionRest {

    @Autowired
    private IRedSocialInvestigacionService service;

    @GetMapping({"/redSocialInvestigacion/listAll", "/redSocialIvestigacion/listAll/"})
    public List<RedSocialInvestigacion> listAll() {
        return service.findAll();
    }

    @GetMapping("/redSocialInvestigacion/listById/{id}")
    public RedSocialInvestigacion listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/redSocialInvestigacion/save", "/redSocialInvestigacion/save/"})
    public void save(@RequestBody RedSocialInvestigacion redSocialInvestigacion) {
        service.save(redSocialInvestigacion);
    }

    @DeleteMapping("/redSocialInvestigacion/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/redSocialInvestigacion/update/{id}")
    public void update(@PathVariable Long id, @RequestBody RedSocialInvestigacion redSocialInvestigacion) {
        service.update(id, redSocialInvestigacion);
    }

}
