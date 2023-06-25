package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.object.RegisterObject;

public interface RegisterService {

    String registerNewDocentByHimself(RegisterObject registerObject);

    String registerNewDocentByAnotherOne(RegisterObject registerObject);

}
