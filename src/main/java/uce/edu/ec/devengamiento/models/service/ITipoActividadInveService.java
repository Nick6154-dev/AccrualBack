package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInno;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInve;

import java.util.List;

public interface ITipoActividadInveService {

    List<TipoActividadInve> findAll();

    TipoActividadInve findById(Long id);

    void save(TipoActividadInve tipoActividadInve);

    void delete(Long id);

}
