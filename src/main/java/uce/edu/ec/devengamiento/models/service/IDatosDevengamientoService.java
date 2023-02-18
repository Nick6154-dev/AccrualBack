package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;

import java.util.List;

public interface IDatosDevengamientoService {

    List<DatosDevengamiento> findAll();

    DatosDevengamiento findById(Long id);

    DatosDevengamiento findByIdDocente(Long idDocente);

    void save(DatosDevengamiento datosDevengamiento, Long idDocente);

    void deleteById(Long id);

    void update(Long id, DatosDevengamiento datosDevengamiento);

}
