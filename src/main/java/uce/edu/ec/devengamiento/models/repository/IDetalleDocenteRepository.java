package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.DetalleDocente;

public interface IDetalleDocenteRepository extends CrudRepository<DetalleDocente, Long> {
}
