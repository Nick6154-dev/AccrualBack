package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;
import uce.edu.ec.devengamiento.models.repository.IPlanDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.IPlanDevengamientoService;

import java.util.List;

@Service
public class PlanDevengamientoServiceImpl implements IPlanDevengamientoService {

    @Autowired
    private IPlanDevengamientoRepository repository;

    @Override
    public List<PlanDevengamiento> findAll() {
        return (List<PlanDevengamiento>) repository.findAll();
    }

    @Override
    public PlanDevengamiento findById(Long id) {
        return repository.findById(id).orElse(new PlanDevengamiento());
    }

    @Override
    public void save(PlanDevengamiento planDevengamiento) {
        repository.save(planDevengamiento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, PlanDevengamiento planDevengamiento) {
        if (repository.existsById(id)) {
            repository.save(planDevengamiento);
        }
    }
}
