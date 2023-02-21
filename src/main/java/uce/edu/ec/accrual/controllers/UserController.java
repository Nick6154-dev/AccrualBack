package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.User;
import uce.edu.ec.accrual.models.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/usuario")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<User> listAll() {
        return service.findAll();
    }

    @GetMapping("/findByUsername/{username}")
    public User listByUsername(@PathVariable String username) {
        return service.findUserByUsername(username);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody User usuario) {
        service.save(usuario);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody User usuario) {
        service.update(id, usuario);
    }

}
