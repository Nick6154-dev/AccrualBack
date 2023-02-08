package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.ActInvestigadora;
import uce.edu.ec.devengamiento.models.service.IActInvestigadoraService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/actInvestigadora")
public class ActInvestigadoraRest {

    @Autowired
    private IActInvestigadoraService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<ActInvestigadora> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ActInvestigadora findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody ActInvestigadora actInvestigadora) {
        service.save(actInvestigadora);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ActInvestigadora actInvestigadora) {
        service.update(id, actInvestigadora);
    }

}
