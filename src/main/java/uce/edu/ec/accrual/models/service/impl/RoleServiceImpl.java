package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.entity.Role;
import uce.edu.ec.accrual.models.repository.RoleRepository;
import uce.edu.ec.accrual.models.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return (List<Role>) repository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElse(new Role());
    }

    @Override
    public void save(Role rol) {
        repository.save(rol);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Role rol) {
        if (repository.existsById(id)) {
            repository.save(rol);
        }
    }
}
