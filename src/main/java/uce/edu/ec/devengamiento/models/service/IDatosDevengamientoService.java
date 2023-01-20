package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;

import java.util.List;

public interface IDatosDevengamientoService {

    List<DatosDevengamiento> findAll();

    DatosDevengamiento findById(Long id);

    void save(DatosDevengamiento datosDevengamiento);

    void delete(Long id);

}