package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.Facultad;

import java.util.List;

public interface IFacultadService {

    List<Facultad> findAll();

    List<Facultad> findFacultadsByIdUniversidad(Long idUniversidad);

    Facultad findById(Long id);

    void save(Facultad facultad);

    void deleteById(Long id);

    void update(Long id, Facultad facultad);

}
