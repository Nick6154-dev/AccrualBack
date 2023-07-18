package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person findById(Long idPerson);

    Person save(Person person);

    String delete(Person person);

    String deleteById(Long idPerson);

    Person update(Person person, Long idPerson);

    boolean existsByEmail(String email);

    boolean existsByIdentification(String identification);

}
