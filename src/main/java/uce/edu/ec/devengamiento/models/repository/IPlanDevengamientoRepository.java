package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IPlanDevengamientoRepository extends CrudRepository<PlanDevengamiento, Long> {

    List<PlanDevengamiento> findPlanDevengamientosByIdDocente(Docente docente);

}
