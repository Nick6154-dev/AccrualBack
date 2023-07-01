package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.object.RegisterObject;

import java.util.List;

public interface RegisterService {

    String registerNewDocentByHimself(RegisterObject registerObject);

    String registerNewDocentByAnotherOne(RegisterObject registerObject);

    List<Person> listDocentsWithOutUser();

    String registerNewUserToDocentWithOutIt(Long idPerson);

    void deleteDocentNotApproved(Long idPerson);

}
