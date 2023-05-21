package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Person;
import uce.edu.ec.accrual.models.repository.PersonRepository;
import uce.edu.ec.accrual.models.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return Optional.of((List<Person>) repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Long idPerson) {
        return repository.findById(idPerson).orElseGet(Person::new);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return repository.findByEmailAndIdentification(person.getEmail(), person.getIdentification()).orElseGet(() -> repository.save(person));
    }

    @Override
    @Transactional
    public String delete(Person person) {
        return repository.findById(person.getIdPerson()).map(value -> "Eliminado con exito")
                .orElseGet(() -> "No se pudo eliminar");
    }

    @Override
    @Transactional
    public String deleteById(Long idPerson) {
        return repository.findById(idPerson).map(value -> "Eliminado con exito")
                .orElseGet(() -> "No se pudo eliminar");
    }

    @Override
    @Transactional
    public Person update(Person person, Long idPerson) {
        return repository.findByEmailAndIdentification(person.getEmail(), person.getIdentification())
                .map(value -> new Person()).orElseGet(() -> {
                    if (repository.existsById(idPerson)) {
                        return new Person();
                    }
                    return repository.save(person);
                });
    }

}
