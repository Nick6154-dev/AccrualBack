package uce.edu.ec.devengamiento.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.devengamiento.models.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, Long> {
}