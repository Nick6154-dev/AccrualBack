package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Usuario;
import uce.edu.ec.devengamiento.models.service.IUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/accrual/api/usuario")
public class UsuarioRest {

    @Autowired
    private IUsuarioService service;

    @GetMapping({"/findAll", "/findAll/"})
    public List<Usuario> listAll() {
        return service.findAll();
    }

    @GetMapping("/findByUsername/{username}")
    public Usuario listByUsername(@PathVariable String username) {
        return service.findUserByUsername(username);
    }

    @PostMapping({"/save", "/save/"})
    public void save(@RequestBody Usuario usuario) {
        service.save(usuario);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Usuario usuario) {
        service.update(id, usuario);
    }

}
