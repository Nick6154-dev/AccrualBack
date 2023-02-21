package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUsuarioByUsername(String nickName);

}
