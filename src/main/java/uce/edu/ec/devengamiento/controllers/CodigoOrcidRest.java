package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.CodigoOrcid;
import uce.edu.ec.devengamiento.models.service.ICodigoOrcidService;

import java.util.List;

@RestController
public class CodigoOrcidRest {

    @Autowired
    private ICodigoOrcidService service;

    @GetMapping({"/codigoOrcid/listAll", "/codigoOrcid/listAll/"})
    public List<CodigoOrcid> listAllCodigoOrcid() {
        return service.findAll();
    }

    @GetMapping("/codigoOrcid/listById/{id}")
    public CodigoOrcid listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/codigoOrcid/save", "/codigoOrcid/save/"})
    public void save(@RequestBody CodigoOrcid codigoOrcid) {
        service.save(codigoOrcid);
    }

    @DeleteMapping("/codigoOrcid/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/codigoOrcid/update/{id}")
    public void update(@PathVariable Long id, @RequestBody CodigoOrcid codigoOrcid) {
        service.update(id, codigoOrcid);
    }

}
