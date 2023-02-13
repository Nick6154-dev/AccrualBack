package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;

import java.util.List;

public interface IActividadDevengamietoService {

    List<ActividadDevengamiento> findAll();

    List<ActividadDevengamiento> findAllByIdPlanDevengamiento(Long idPlan);

    ActividadDevengamiento findById(Long id);

    void save(ActividadDevengamiento actividadDevengamiento);

    void deleteById(Long id);

    void update(Long id, ActividadDevengamiento actividadDevengamiento);

}
