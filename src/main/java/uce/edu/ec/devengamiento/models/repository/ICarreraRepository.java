package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Carrera;

import java.util.List;

public interface ICarreraRepository extends CrudRepository<Carrera, Long> {

    List<Carrera> findCarrerasByIdFacultad(Long idFacultad);

}
