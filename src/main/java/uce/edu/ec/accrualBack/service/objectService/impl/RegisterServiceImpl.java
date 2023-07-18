package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.entity.Role;
import uce.edu.ec.accrualBack.entity.User;
import uce.edu.ec.accrualBack.object.RegisterObject;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.RegisterService;

import java.util.*;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private PersonService personService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private AccrualDataService accrualDataService;

    @Autowired
    private NetworkService networkService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Override
    public Map<Integer, String> registerNewDocentByHimself(RegisterObject registerObject) {
        Map<Integer, String> response = validateEmailAndIdentification(registerObject);
        if (response.containsKey(400)) return response;
        Docent docent = registerNewDocent(registerObject);
        registerObject.getAccrualData().setDocent(docent);
        registerObject.getAccrualData().setSettlement(false);
        accrualDataService.save(registerObject.getAccrualData());
        registerObject.getNetwork().setDocent(docent);
        networkService.save(registerObject.getNetwork());
        mailService.sendNewDocentStateNotificationMail(docent.getIdPerson());
        response.put(200, "Nuevo docente registrado con exito");
        return response;
    }

    @Override
    public Map<Integer, String> registerNewDocentByAnotherOne(RegisterObject registerObject) {
        Map<Integer, String> response = validateEmailAndIdentification(registerObject);
        if (response.containsKey(400)) return response;
        Docent docent = registerNewDocent(registerObject);
        registerNewUser(docent);
        response.put(200, "Nuevo docente registrado con exito");
        return response;
    }

    @Override
    public List<Person> listDocentsWithOutUser() {
        List<User> users = userService.findAll();
        List<Person> people = personService.findAll();
        for (User u : users) {
            people.removeIf(p -> Objects.equals(p.getIdPerson(), u.getIdPerson()));
        }
        return people;
    }

    @Override
    public Map<Integer, String> registerNewUserToDocentWithOutIt(Long idPerson) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        registerNewUser(docent);
        response.put(200, "Usuario registrado con exito");
        return response;
    }

    @Override
    public Map<Integer, String> registerAllNewUsersToDocents() {
        Map<Integer, String> response = new HashMap<>();
        List<Person> people = this.listDocentsWithOutUser();
        for (Person person : people) {
            this.registerNewUserToDocentWithOutIt(person.getIdPerson());
        }
        response.put(200, "Docentes registrados de una");
        return response;
    }

    @Override
    public Map<Integer, String> deleteDocentNotApproved(Long idPerson) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        networkService.deleteById(networkService.findByDocent(docent).getIdNetworks());
        accrualDataService.deleteById(accrualDataService.findByDocent(docent).getIdAccrualData());
        docentService.deleteById(docent.getIdDocent());
        response.put(200, "Docente eliminado por no ser aprobado");
        return response;
    }

    private Map<Integer, String> validateEmailAndIdentification(RegisterObject registerObject) {
        Map<Integer, String> response = new HashMap<>();
        if (personService.existsByEmail(registerObject.getPerson().getEmail())) {
            response.put(400, "Ya existe un usuario con ese correo registrado");
            return response;
        }
        if (personService.existsByIdentification(registerObject.getPerson().getIdentification())) {
            response.put(400, "Ya existe un usuario con esa cedula/pasaporte registrado");
            return response;
        }
        return response;
    }

    private Docent registerNewDocent(RegisterObject registerObject) {
        Person person = personService.save(registerObject.getPerson());
        registerObject.getDocent().setIdPerson(person.getIdPerson());
        registerObject.getDocent().setFaculty(
                facultyService.findById(Long.valueOf(registerObject.getDocent().getFaculty())).getFacultyName());
        return docentService.save(registerObject.getDocent());
    }

    private void registerNewUser(Docent docent) {
        Role role = roleService.findRoleByRoleName("ROLE_USER");
        Person person = personService.findById(docent.getIdPerson());
        userService.save(new User(person.getEmail(), person.getIdentification(), person.getIdPerson(),
                Collections.singletonList(role)));
        mailService.sendNewUserNotificationMail(person.getIdPerson());
    }

}
