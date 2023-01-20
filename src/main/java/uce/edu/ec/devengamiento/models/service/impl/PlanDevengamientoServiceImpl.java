package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;
import uce.edu.ec.devengamiento.models.repository.IPlanDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.IPlanDevengamientoService;

import java.util.List;

@Service
public class PlanDevengamientoServiceImpl implements IPlanDevengamientoService {

    @Autowired
    private IPlanDevengamientoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<PlanDevengamiento> findAll() {
        return (List<PlanDevengamiento>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public PlanDevengamiento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(PlanDevengamiento planDevengamiento) {
        repository.save(planDevengamiento);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
