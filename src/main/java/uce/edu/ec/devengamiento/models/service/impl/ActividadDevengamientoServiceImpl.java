package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;
import uce.edu.ec.devengamiento.models.repository.IActividadDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.IActividadDevengamietoService;
import uce.edu.ec.devengamiento.models.service.IPlanDevengamientoService;

import java.util.List;

@Service
public class ActividadDevengamientoServiceImpl implements IActividadDevengamietoService {

    @Autowired
    private IActividadDevengamientoRepository repository;

    @Autowired
    private IPlanDevengamientoService service;

    @Override
    public List<ActividadDevengamiento> findAll() {
        return (List<ActividadDevengamiento>) repository.findAll();
    }

    @Override
    public List<ActividadDevengamiento> findAllByIdPlanDevengamiento(Long idPlan) {
        return repository.findActividadDevengamientosByIdPlanDevengamiento(service.findById(idPlan));
    }

    @Override
    public ActividadDevengamiento findById(Long id) {
        return repository.findById(id).orElse(new ActividadDevengamiento());
    }

    @Override
    public void save(ActividadDevengamiento actividadDevengamiento) {
        repository.save(actividadDevengamiento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, ActividadDevengamiento actividadDevengamiento) {
        if (repository.existsById(id)) {
            repository.save(actividadDevengamiento);
        }
    }
}
