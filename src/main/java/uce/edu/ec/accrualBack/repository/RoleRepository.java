package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findRoleByRoleName(String roleName);

}
