package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.Role;
import uce.edu.ec.accrual.models.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/rol")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<Role> listAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public Role listById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody Role rol) {
        service.save(rol);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Role rol) {
        service.update(id, rol);
    }

}
