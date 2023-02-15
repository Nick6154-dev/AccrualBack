package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;
import uce.edu.ec.devengamiento.models.repository.IPlanDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.IDocenteService;
import uce.edu.ec.devengamiento.models.service.IPlanDevengamientoService;

import java.util.List;

@Service
public class PlanDevengamientoServiceImpl implements IPlanDevengamientoService {

    @Autowired
    private IPlanDevengamientoRepository repository;

    @Autowired
    private IDocenteService docenteService;

    @Override
    public List<PlanDevengamiento> findAll() {
        return (List<PlanDevengamiento>) repository.findAll();
    }

    @Override
    public List<PlanDevengamiento> findByIdDocente(Long idDocente) {
        return repository.findPlanDevengamientosByIdDocente(docenteService.findById(idDocente));
    }

    @Override
    public PlanDevengamiento findById(Long id) {
        return repository.findById(id).orElse(new PlanDevengamiento());
    }

    @Override
    public PlanDevengamiento save(Long idDocente, PlanDevengamiento planDevengamiento) {
        planDevengamiento.setIdDocente(docenteService.findById(idDocente));
        List<PlanDevengamiento> planesDocente = findByIdDocente(idDocente);
        if (planesDocente.isEmpty()) {
            planDevengamiento.setNumeroPlan(1);
        } else {
            planDevengamiento.setNumeroPlan(repository.findMaxPlanByIdDocente(docenteService.findById(idDocente)).getNumeroPlan() + 1);
        }
        planDevengamiento = repository.save(planDevengamiento);
        return planDevengamiento;
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
