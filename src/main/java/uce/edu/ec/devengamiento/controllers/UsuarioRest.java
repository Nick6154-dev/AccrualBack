package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.Usuario;
import uce.edu.ec.devengamiento.models.service.IUsuarioService;

import java.util.List;

@RestController
public class UsuarioRest {

    @Autowired
    private IUsuarioService service;

    @GetMapping({"/usuario/listAll", "/usuario/listAll/"})
    public List<Usuario> listAll() {
        return service.findAll();
    }

    @GetMapping("/usuario/listByUsername/{username}")
    public Usuario listByUsername(@PathVariable String username) {
        return service.findUserByNickname(username);
    }

    @PostMapping({"/usuario/save", "/usuario/save/"})
    public void save(@RequestBody Usuario usuario) {
        service.save(usuario);
    }

    @DeleteMapping("/usuario/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/usuario/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Usuario usuario) {
        service.update(id, usuario);
    }

}
