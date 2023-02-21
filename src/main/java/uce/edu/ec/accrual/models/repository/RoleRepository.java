package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
