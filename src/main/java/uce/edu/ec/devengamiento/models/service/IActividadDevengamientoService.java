package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;

import java.util.List;

public interface IActividadDevengamientoService {

    List<ActividadDevengamiento> findAll();

    ActividadDevengamiento findById(Long id);

    void save(ActividadDevengamiento actividadDevengamiento);

    void delete(Long id);

}
