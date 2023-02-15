package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.entity.PlanDevengamiento;

import java.util.List;

public interface IPlanDevengamientoRepository extends CrudRepository<PlanDevengamiento, Long> {

    List<PlanDevengamiento> findPlanDevengamientosByIdDocente(Docente docente);

    @Query("SELECT p from PlanDevengamiento p where (select max(pd.numeroPlan) from PlanDevengamiento pd where pd.idDocente = :idDocente) = p.numeroPlan")
    PlanDevengamiento findMaxPlanByIdDocente(@Param("idDocente") Docente idDocente);

}
