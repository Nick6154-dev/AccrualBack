package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActDocente;
import uce.edu.ec.devengamiento.models.service.IActDocenteService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/actDocente")
public class ActDocenteRest {

    @Autowired
    private IActDocenteService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<ActDocente> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ActDocente findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody ActDocente actDocente) {
        service.save(actDocente);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActDocente actDocente) {
        service.update(id, actDocente);
    }

}
