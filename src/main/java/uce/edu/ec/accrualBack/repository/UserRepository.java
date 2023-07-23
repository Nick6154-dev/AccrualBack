package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Role;
import uce.edu.ec.accrualBack.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String nickName);

    List<User> findAllByRolesIs(Role role);


}
