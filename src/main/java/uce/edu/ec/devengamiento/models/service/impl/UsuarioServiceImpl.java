package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Usuario;
import uce.edu.ec.devengamiento.models.repository.IUsuarioRepository;
import uce.edu.ec.devengamiento.models.service.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Usuario usuario) {
        repository.save(usuario);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
