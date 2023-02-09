package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;
import uce.edu.ec.devengamiento.models.entity.Docente;

public interface IDatosDevengamientoRepository extends CrudRepository<DatosDevengamiento, Long> {

    public DatosDevengamiento findDatosDevengamientoByIdDocente(Docente docente);

}
