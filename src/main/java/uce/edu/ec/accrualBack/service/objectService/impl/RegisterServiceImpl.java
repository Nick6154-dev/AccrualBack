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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public String registerNewDocentByHimself(RegisterObject registerObject) {
        Docent docent = registerNewDocent(registerObject);
        registerObject.getAccrualData().setDocent(docent);
        registerObject.getAccrualData().setSettlement(false);
        accrualDataService.save(registerObject.getAccrualData());
        registerObject.getNetwork().setDocent(docent);
        networkService.save(registerObject.getNetwork());
        mailService.sendNewDocentStateNotificationMail(docent.getIdPerson());
        return "Nuevo docente registrado con exito";
    }

    @Override
    public String registerNewDocentByAnotherOne(RegisterObject registerObject) {
        Docent docent = registerNewDocent(registerObject);
        registerNewUser(docent);
        return "Nuevo docente registrado con exito";
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
    public String registerNewUserToDocentWithOutIt(Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        registerNewUser(docent);
        return "Usuario registrado con exito";
    }

    @Override
    public void deleteDocentNotApproved(Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        networkService.deleteById(networkService.findByDocent(docent).getIdNetworks());
        accrualDataService.deleteById(accrualDataService.findByDocent(docent).getIdAccrualData());
        docentService.deleteById(docent.getIdDocent());
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
