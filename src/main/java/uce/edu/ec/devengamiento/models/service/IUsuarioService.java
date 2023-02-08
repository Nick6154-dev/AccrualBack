package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> findAll();

    Usuario findById(Long id);

    Usuario findUserByUsername(String username);

    void save(Usuario usuario);

    void deleteById(Long id);

    void update(Long id, Usuario usuario);

}
