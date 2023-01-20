package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

}
