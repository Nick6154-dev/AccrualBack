package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Carrera;
import uce.edu.ec.devengamiento.models.entity.Facultad;

import java.util.List;

public interface ICarreraRepository extends CrudRepository<Carrera, Long> {

    List<Carrera> findCarreraByIdFacultad(Long idFacultad);

}
