package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Facultad;
import uce.edu.ec.devengamiento.models.entity.Universidad;

import java.util.List;

public interface IFacultadRepository extends CrudRepository<Facultad, Long> {

    List<Facultad> findFacultadsByIdUniversidad(Universidad idUniversidad);

}
