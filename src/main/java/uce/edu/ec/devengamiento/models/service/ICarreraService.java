package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.Carrera;

import java.util.List;

public interface ICarreraService {

    List<Carrera> findAll();

    List<Carrera> findCarrerasByIdFacultad(Long idFacultad);

    Carrera findById(Long id);

    void save(Carrera carrera);

    void deleteById(Long id);

    void update(Long id, Carrera carrera);

}
