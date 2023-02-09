package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IPlanDevengamientoRepository extends CrudRepository<PlanDevengamiento, Long> {

    List<PlanDevengamiento> findPlanDevengamientosByIdDocente(Docente docente);

    @Query("SELECT MAX(p.numeroPlan) FROM PlanDevengamiento p WHERE p.idDocente = :idDocente")
    Integer findMaxNumeroPlanByIdDocente(@Param("idDocente") Integer idDocente);

}
