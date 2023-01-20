package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Role;
import uce.edu.ec.devengamiento.models.service.IRoleService;

import java.util.List;

@RestController
public class RoleRest {

    @Autowired
    private IRoleService service;

    @GetMapping({"/rol/listAll", "/rol/listAll/"})
    public List<Role> listAll() {
        return service.findAll();
    }

    @GetMapping("/rol/listById/{id}")
    public Role listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/rol/save", "/rol/save/"})
    public void save(@RequestBody Role rol) {
        service.save(rol);
    }

    @DeleteMapping("/rol/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/rol/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Role rol) {
        service.update(id, rol);
    }

}
