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

import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of((List<Person>) repository.findAll()).map(value ->
                ResponseEntity.status(HttpStatus.ACCEPTED).body(value)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idPerson) {
        return repository.findById(idPerson).map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Person()));
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Person person) {
        return repository.findByEmailAndIdentification(person.getEmail(), person.getIdentification()).map(value ->
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Person())
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(person)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Person person) {
        return repository.findById(person.getIdPerson()).map(value -> {
            repository.delete(value);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se pudo eliminar"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idPerson) {
        return repository.findById(idPerson).map(value -> {
            repository.deleteById(value.getIdPerson());
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Person person, Long idPerson) {
        return repository.findByEmailAndIdentification(person.getEmail(), person.getIdentification())
                .map(value -> ResponseEntity.badRequest().build()).orElseGet(() -> {
                    if (repository.existsById(idPerson)) {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Person());
                    }
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(person));
                });
    }

}
