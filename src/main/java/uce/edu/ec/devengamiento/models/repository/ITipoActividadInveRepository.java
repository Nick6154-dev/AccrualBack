package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInno;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInve;

public interface ITipoActividadInveRepository extends CrudRepository<TipoActividadInve, Long> {

}
