package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadDocente;

import java.util.List;

public interface ITipoActividadDocenteService {

    List<TipoActividadDocente> findAll();

    TipoActividadDocente findById(Long id);

    void save(TipoActividadDocente tipoActividadDocente);

    void delete(Long id);

}
