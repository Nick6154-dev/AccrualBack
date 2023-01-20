package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.TipoInstitucionUniversidad;

import java.util.List;

public interface ITipoInstitucionUniversidadService {

    List<TipoInstitucionUniversidad> findAll();

    TipoInstitucionUniversidad findById(Long id);

    void save(TipoInstitucionUniversidad tipoInstitucionUniversidad);

    void delete(Long id);

}
