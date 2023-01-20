package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Docente;

public interface IDocenteRepository extends CrudRepository<Docente, Long> {
}
