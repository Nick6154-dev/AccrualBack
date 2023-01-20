package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.ActividadInvestigadora;

import java.util.List;

public interface IActividadInvestigadoraService {

    List<ActividadInvestigadora> findAll();

    ActividadInvestigadora findById(Long id);

    void save(ActividadInvestigadora actividadInvestigadora);

    void delete(Long id);

}
