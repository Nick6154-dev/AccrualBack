package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IPlanDevengamientoService {

    List<PlanDevengamiento> findAll();

    List<PlanDevengamiento> findByIdDocente(Long idDocente);

    PlanDevengamiento findById(Long id);

    void save(Long idDocente, PlanDevengamiento planDevengamiento);

    void deleteById(Long id);

    void update(Long id, PlanDevengamiento planDevengamiento);

}
