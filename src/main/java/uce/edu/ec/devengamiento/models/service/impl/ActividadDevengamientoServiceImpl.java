package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;
import uce.edu.ec.devengamiento.models.repository.IActividadDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.IActividadDevengamientoService;

import java.util.List;

@Service
public class ActividadDevengamientoServiceImpl implements IActividadDevengamientoService {

    @Autowired
    private IActividadDevengamientoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ActividadDevengamiento> findAll() {
        return (List<ActividadDevengamiento>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ActividadDevengamiento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(ActividadDevengamiento actividadDevengamiento) {
        repository.save(actividadDevengamiento);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
