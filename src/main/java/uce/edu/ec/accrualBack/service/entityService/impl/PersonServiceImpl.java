package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.repository.PersonRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PersonService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.RoleService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private DocentService docentService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return Optional.of((List<Person>) repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findALlPeopleWithSettlementNotApprovedAndBeUser() {
        List<Map<String, Object>> response = new ArrayList<>();
        List<Docent> docents = userService.findAllByRolesIs(roleService.findRoleByRoleName("ROLE_USER"))
                .stream()
                .map(user -> docentService.findByIdPerson(user.getIdPerson()))
                .filter(
                        docent -> docentService.findAllDocentSettlementApproved()
                                .stream()
                                .anyMatch(aux -> !docent.equals(aux))
                ).collect(Collectors.toList());
        for (Docent docent : docents) {
            Map<String, Object> aux = new HashMap<>();
            aux.put("person", findById(docent.getIdPerson()));
            aux.put("faculty", docent.getFaculty());
            response.add(aux);
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Long idPerson) {
        return repository.findById(idPerson).orElseGet(Person::new);
    }

    @Override
    public Person findByEmail(String email) {
        return repository.findByEmail(email);
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

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdentification(String identification) {
        return repository.existsByIdentification(identification);
    }

}
