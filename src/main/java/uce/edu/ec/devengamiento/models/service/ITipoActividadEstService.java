package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadEst;

import java.util.List;

public interface ITipoActividadEstService {

    List<TipoActividadEst> findAll();

    TipoActividadEst findById(Long id);

    void save(TipoActividadEst tipoActividadEst);

    void delete(Long id);

}
