package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.object.RegisterObject;

import java.util.List;
import java.util.Map;

public interface RegisterService {

    Map<Integer, String> registerNewDocentByHimself(RegisterObject registerObject);

    Map<Integer, String> registerNewDocentByAnotherOne(RegisterObject registerObject);

    List<Person> listDocentsWithOutUser();

    Map<Integer, String> registerNewUserToDocentWithOutIt(Long idPerson);

    Map<Integer, String> registerAllNewUsersToDocents();

    Map<Integer, String> deleteDocentNotApproved(Long idPerson);

}
