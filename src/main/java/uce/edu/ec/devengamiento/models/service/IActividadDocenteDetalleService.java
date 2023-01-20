package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.ActividadDocente;
import uce.edu.ec.devengamiento.models.entity.ActividadDocenteDetalle;

import java.util.List;

public interface IActividadDocenteDetalleService {

    List<ActividadDocenteDetalle> findAll();

    ActividadDocenteDetalle findById(Long id);

    void save(ActividadDocenteDetalle actividadDocenteDetalle);

    void delete(Long id);

}
