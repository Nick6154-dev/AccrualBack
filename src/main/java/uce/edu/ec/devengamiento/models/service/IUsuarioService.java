package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> findAll();

    Usuario findById(Long id);

    void save(Usuario usuario);

    void delete(Long id);

}
