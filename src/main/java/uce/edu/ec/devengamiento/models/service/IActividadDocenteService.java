package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.ActividadDocente;

import java.util.List;

public interface IActividadDocenteService {

    List<ActividadDocente> findAll();

    ActividadDocente findById(Long id);

    void save(ActividadDocente actividadDocente);

    void delete(Long id);

}
