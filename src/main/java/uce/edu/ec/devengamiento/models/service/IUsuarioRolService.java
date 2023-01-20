package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.UsuarioRol;

import java.util.List;

public interface IUsuarioRolService {

    List<UsuarioRol> findAll();

    UsuarioRol findById(Long id);

    void save(UsuarioRol universidad);

    void delete(Long id);

}
