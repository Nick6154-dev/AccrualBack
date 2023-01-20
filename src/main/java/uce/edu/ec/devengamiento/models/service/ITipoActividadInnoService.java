package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInno;

import java.util.List;

public interface ITipoActividadInnoService {

    List<TipoActividadInno> findAll();

    TipoActividadInno findById(Long id);

    void save(TipoActividadInno tipoActividadInno);

    void delete(Long id);

}
