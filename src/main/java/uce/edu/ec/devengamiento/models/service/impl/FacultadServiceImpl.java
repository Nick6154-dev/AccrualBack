package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Facultad;
import uce.edu.ec.devengamiento.models.repository.IFacultadRepository;
import uce.edu.ec.devengamiento.models.service.IFacultadService;
import uce.edu.ec.devengamiento.models.service.IUniversidadService;

import java.util.List;

@Service
public class FacultadServiceImpl implements IFacultadService {

    @Autowired
    private IFacultadRepository repository;

    @Autowired
    private IUniversidadService service;

    @Override
    public List<Facultad> findAll() {
        return (List<Facultad>) repository.findAll();
    }

    @Override
    public List<Facultad> findFacultadsByIdUniversidad(Long idUniversidad) {
        return repository.findFacultadsByIdUniversidad(service.findById(idUniversidad));
    }

    @Override
    public Facultad findById(Long id) {
        return repository.findById(id).orElse(new Facultad());
    }

    @Override
    public void save(Facultad facultad) {
        repository.save(facultad);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Facultad facultad) {
        if (repository.existsById(id)) {
            repository.save(facultad);
        }
    }
}
