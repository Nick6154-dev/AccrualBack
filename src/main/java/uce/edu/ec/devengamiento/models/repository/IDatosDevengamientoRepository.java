package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;

public interface IDatosDevengamientoRepository extends CrudRepository<DatosDevengamiento, Long> {
}
