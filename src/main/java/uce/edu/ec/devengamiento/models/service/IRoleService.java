package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    Role findById(Long id);

    void save(Role rol);

    void deleteById(Long id);

    void update(Long id, Role rol);

}
