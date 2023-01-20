package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.ActividadDocente;
import uce.edu.ec.devengamiento.models.entity.ActividadDocenteDetalle;

public interface IActividadDocenteDetalleRepository extends CrudRepository<ActividadDocenteDetalle, Long> {

}
