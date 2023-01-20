package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.UsuarioRol;
import uce.edu.ec.devengamiento.models.repository.IUsuarioRolRepository;
import uce.edu.ec.devengamiento.models.service.IUsuarioRolService;

import java.util.List;

@Service
public class UsuarioRolServiceImpl implements IUsuarioRolService {

    @Autowired
    private IUsuarioRolRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioRol> findAll() {
        return (List<UsuarioRol>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioRol findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(UsuarioRol usuario) {
        repository.save(usuario);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
