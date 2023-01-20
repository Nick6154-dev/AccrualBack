package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.TipoActividad;

import java.util.List;

public interface ITipoActividadService {

    List<TipoActividad> findAll();

    TipoActividad findById(Long id);

    void save(TipoActividad tipoActividad);

    void deleteById(Long id);

    void update(Long id, TipoActividad tipoActividad);

}
