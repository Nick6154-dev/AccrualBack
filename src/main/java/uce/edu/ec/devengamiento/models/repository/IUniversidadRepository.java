package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Universidad;

public interface IUniversidadRepository extends CrudRepository<Universidad, Long> {
}
