package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Carrera;
import uce.edu.ec.devengamiento.models.repository.ICarreraRepository;
import uce.edu.ec.devengamiento.models.service.ICarreraService;
import uce.edu.ec.devengamiento.models.service.IFacultadService;

import java.util.List;

@Service
public class CarreraServiceImpl implements ICarreraService {

    @Autowired
    private ICarreraRepository repository;

    @Autowired
    private IFacultadService service;

    @Override
    public List<Carrera> findAll() {
        return (List<Carrera>) repository.findAll();
    }

    @Override
    public List<Carrera> findCarrerasByIdFacultad(Long idFacultad) {
        return repository.findCarrerasByIdFacultad(service.findById(idFacultad));
    }

    @Override
    public Carrera findById(Long id) {
        return repository.findById(id).orElse(new Carrera());
    }

    @Override
    public void save(Carrera carrera) {
        repository.save(carrera);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Carrera carrera) {
        if (repository.existsById(id)) {
            repository.save(carrera);
        }
    }
}
