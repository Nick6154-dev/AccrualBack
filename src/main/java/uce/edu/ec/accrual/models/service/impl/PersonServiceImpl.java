package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import uce.edu.ec.accrual.models.entity.Person;
import uce.edu.ec.accrual.models.repository.PersonRepository;
import uce.edu.ec.accrual.models.service.PersonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        Optional<List<Person>> people = Optional.of((List<Person>) repository.findAll());
        return people.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idPerson) {
        Optional<Person> person = repository.findById(idPerson);
        return person.map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Person person) {
        Optional<Person> optionalPerson = repository
                .findByEmailAndIdentification(person.getEmail(), person.getIdentification());
        return optionalPerson.map(value -> ResponseEntity
                        .status(HttpStatus.BAD_REQUEST).build())
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(person)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Person person) {
        Optional<Person> optionalPerson = repository.findById(person.getIdPerson());
        return optionalPerson.map(value -> {
            repository.delete(value);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idPerson) {
        Optional<Person> optionalPerson = repository.findById(idPerson);
        return optionalPerson.map(value -> {
            repository.deleteById(value.getIdPerson());
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Person person, Long idPerson) {
        Optional<Person> optionalPerson = repository.findByEmailAndIdentification(person.getEmail(), person.getIdentification());
        return optionalPerson.map(value -> ResponseEntity.badRequest().build())
                .orElseGet(() -> {
                    if (repository.existsById(idPerson)) {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
                    }
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(person));
                });
    }

    @Override
    public ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        result.getFieldErrors().forEach(err -> map
                .put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }

}
