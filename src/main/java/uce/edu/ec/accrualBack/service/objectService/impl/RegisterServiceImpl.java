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

import java.util.Collections;

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

    private Docent registerNewDocent(RegisterObject registerObject) {
        Person person = personService.save(registerObject.getPerson());
        Role role = roleService.findRoleByRoleName("ROLE_USER");
        User user = userService.save(new User(person.getEmail(), person.getIdentification(), person.getIdPerson(),
                Collections.singletonList(role)));
        registerObject.getDocent().setIdPerson(person.getIdPerson());
        registerObject.getDocent().setFaculty(
                facultyService.findById(Long.valueOf(registerObject.getDocent().getFaculty())).getFacultyName());
        mailService.sendNewDocentNotificationMail(person.getIdPerson());
        return docentService.save(registerObject.getDocent());
    }

    @Override
    public String registerNewDocentByHimself(RegisterObject registerObject) {
        Docent docent = registerNewDocent(registerObject);
        registerObject.getAccrualData().setDocent(docent);
        registerObject.getAccrualData().setSettlement(false);
        accrualDataService.save(registerObject.getAccrualData());
        registerObject.getNetwork().setDocent(docent);
        networkService.save(registerObject.getNetwork());
        return "Nuevo docente registrado con exito";
    }

    @Override
    public String registerNewDocentByAnotherOne(RegisterObject registerObject) {
        registerNewDocent(registerObject);
        return "Nuevo docente registrado con exito";
    }

}
