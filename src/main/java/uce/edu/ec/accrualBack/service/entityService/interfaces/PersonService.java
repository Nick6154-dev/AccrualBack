package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {

    List<Person> findAll();

    List<Map<String, Object>> findALlPeopleWithSettlementNotApprovedAndBeUser();

    Person findById(Long idPerson);

    Person findByEmail(String email);

    Person save(Person person);

    String delete(Person person);

    String deleteById(Long idPerson);

    Person update(Person person, Long idPerson);

    boolean existsByEmail(String email);

    boolean existsByIdentification(String identification);

}
