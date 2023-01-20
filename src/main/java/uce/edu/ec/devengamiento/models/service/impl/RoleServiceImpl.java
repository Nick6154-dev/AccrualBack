package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Role;
import uce.edu.ec.devengamiento.models.repository.IRoleRepository;
import uce.edu.ec.devengamiento.models.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository repository;

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
