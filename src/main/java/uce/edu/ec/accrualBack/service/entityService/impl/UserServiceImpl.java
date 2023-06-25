package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.User;
import uce.edu.ec.accrualBack.repository.UserRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(new User());
    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUsuarioByUsername(username);
    }

    @Override
    public void save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, User user) {
        if (repository.existsById(id)) {
            repository.save(user);
        }
    }

}
