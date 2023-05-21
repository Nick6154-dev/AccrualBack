package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person findById(Long idPerson);

    Person save(Person person);

    String delete(Person person);

    String deleteById(Long idPerson);

    Person update(Person person, Long idPerson);

}
