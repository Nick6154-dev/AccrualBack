package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Role;
import uce.edu.ec.accrualBack.repository.RoleRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return (List<Role>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Long id) {
        return repository.findById(id).orElse(new Role());
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleByRoleName(String roleName) {
        return repository.findRoleByRoleName(roleName).orElseGet(Role::new);
    }

    @Override
    @Transactional
    public void save(Role rol) {
        repository.save(rol);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Role rol) {
        if (repository.existsById(id)) {
            repository.save(rol);
        }
    }
}
