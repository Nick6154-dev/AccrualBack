package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import uce.edu.ec.accrual.models.entity.Person;

import java.util.Map;

public interface PersonService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idPerson);

    ResponseEntity<?> save(Person person);

    ResponseEntity<?> delete(Person person);

    ResponseEntity<?> deleteById(Long idPerson);

    ResponseEntity<?> update(Person person, Long idPerson);

    ResponseEntity<Map<String, String>> validate(BindingResult result);

}
