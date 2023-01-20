package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.UniversidadInstitucion;

import java.util.List;

public interface IUniversidadInstitucionService {

    List<UniversidadInstitucion> findAll();

    UniversidadInstitucion findById(Long id);

    void save(UniversidadInstitucion universidadInstitucion);

    void deleteById(Long id);

    void update(Long id, UniversidadInstitucion universidadInstitucion);

}
