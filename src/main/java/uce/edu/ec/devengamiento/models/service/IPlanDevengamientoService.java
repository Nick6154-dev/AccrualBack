package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IPlanDevengamientoService {

    List<PlanDevengamiento> findAll();

    PlanDevengamiento findById(Long id);

    void save(PlanDevengamiento planDevengamiento);

    void delete(Long id);

}
