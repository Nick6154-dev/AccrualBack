package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Rol;
import uce.edu.ec.devengamiento.models.repository.IRolRepository;
import uce.edu.ec.devengamiento.models.service.IRolService;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Rol> findAll() {
        return (List<Rol>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Rol rol) {
        repository.save(rol);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
