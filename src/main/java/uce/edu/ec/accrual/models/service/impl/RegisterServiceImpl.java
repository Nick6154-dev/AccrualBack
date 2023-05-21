package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Person;
import uce.edu.ec.accrual.models.object.RegisterObject;
import uce.edu.ec.accrual.models.service.*;

import java.util.HashMap;
import java.util.Map;

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

    private Docent registerNewDocent(RegisterObject registerObject) {
        Person person = personService.save(registerObject.getPerson());
        registerObject.getDocent().setIdPerson(person.getIdPerson());
        registerObject.getDocent().setFaculty(
                facultyService.findById(Long.valueOf(registerObject.getDocent().getFaculty())).getFacultyName());
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
        Docent docent = registerNewDocent(registerObject);
        return "Nuevo docente registrado con exito";
    }

}
