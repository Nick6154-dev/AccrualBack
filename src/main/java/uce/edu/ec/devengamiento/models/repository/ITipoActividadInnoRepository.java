package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.TipoActividadDocente;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInno;

public interface ITipoActividadInnoRepository extends CrudRepository<TipoActividadInno, Long> {

}
