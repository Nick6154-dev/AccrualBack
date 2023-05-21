package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.object.RegisterObject;

public interface RegisterService {

    String registerNewDocentByHimself(RegisterObject registerObject);

    String registerNewDocentByAnotherOne(RegisterObject registerObject);

}
