package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.TipoInstitucion;

import java.util.List;

public interface ITipoInstitucionService {

    List<TipoInstitucion> findAll();

    TipoInstitucion findById(Long id);

    void save(TipoInstitucion tipoInstitucion);

    void delete(Long id);

}
