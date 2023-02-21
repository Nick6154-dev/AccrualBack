package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    void save(Role rol);

    void deleteById(Long id);

    void update(Long id, Role rol);

}
