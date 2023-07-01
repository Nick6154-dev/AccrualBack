package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role findRoleByRoleName(String roleName);

    void save(Role rol);

    void deleteById(Long id);

    void update(Long id, Role rol);

}
