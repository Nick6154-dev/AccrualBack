package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IPlanDevengamientoService {

    List<PlanDevengamiento> findAll();

    PlanDevengamiento findById(Long id);

    void save(PlanDevengamiento planDevengamiento);

    void deleteById(Long id);

    void update(Long id, PlanDevengamiento planDevengamiento);

}
