package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findByEmailAndIdentification(String email, String identification);

    boolean existsByEmail(String email);

    boolean existsByIdentification(String identification);

}
