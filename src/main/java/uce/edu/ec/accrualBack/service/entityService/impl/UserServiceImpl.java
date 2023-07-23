package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Role;
import uce.edu.ec.accrualBack.entity.User;
import uce.edu.ec.accrualBack.repository.UserRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.RoleService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public List<User> findAllByRolesIs(Role roles) {
        return repository.findAllByRolesIs(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElse(new User());
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        if (repository.existsById(id)) {
            repository.save(user);
        }
    }

}
