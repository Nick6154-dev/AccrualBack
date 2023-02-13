package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IActividadDevengamientoRepository extends CrudRepository<ActividadDevengamiento, Long> {

    List<ActividadDevengamiento> findActividadDevengamientosByIdPlanDevengamiento(PlanDevengamiento planDevengamiento);

}
