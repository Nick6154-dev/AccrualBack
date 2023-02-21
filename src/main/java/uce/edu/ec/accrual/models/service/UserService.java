package uce.edu.ec.accrual.models.service;


import uce.edu.ec.accrual.models.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findUserByUsername(String username);

    void save(User usuario);

    void deleteById(Long id);

    void update(Long id, User usuario);

}
