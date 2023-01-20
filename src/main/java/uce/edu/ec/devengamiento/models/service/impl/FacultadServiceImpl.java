package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Facultad;
import uce.edu.ec.devengamiento.models.entity.Universidad;
import uce.edu.ec.devengamiento.models.repository.IFacultadRepository;
import uce.edu.ec.devengamiento.models.service.IFacultadService;

import java.util.List;

@Service
public class FacultadServiceImpl implements IFacultadService {

    @Autowired
    private IFacultadRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Facultad> findAll() {
        return (List<Facultad>) repository.findAll();
    }

    @Override
    public List<Facultad> findByIdUniversidad(Long idUniversidad) {
        return repository.findByIdUniversidad(idUniversidad);
    }

    @Transactional(readOnly = true)
    @Override
    public Facultad findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Facultad facultad) {
        repository.save(facultad);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
