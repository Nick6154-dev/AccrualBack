package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUsuarioByUsername(String nickName);

}
