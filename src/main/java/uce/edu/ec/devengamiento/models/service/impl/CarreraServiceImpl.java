package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Carrera;
import uce.edu.ec.devengamiento.models.repository.ICarreraRepository;
import uce.edu.ec.devengamiento.models.service.ICarreraService;

import java.util.List;

@Service
public class CarreraServiceImpl implements ICarreraService {

    @Autowired
    private ICarreraRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Carrera> findAll() {
        return (List<Carrera>) repository.findAll();
    }

    @Override
    public List<Carrera> findByFacultad(Long idFacultad) {
        return repository.findCarreraByIdFacultad(idFacultad);
    }

    @Transactional(readOnly = true)
    @Override
    public Carrera findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Carrera carrera) {
        repository.save(carrera);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
