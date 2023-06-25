package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Type;

import java.util.List;

public interface TypeService {

    List<Type> findAll();

    Type findById(Long idType);

    Type save(Type type);

    String delete(Type type);

    String deleteById(Long idType);

    Type update(Type type, Long idType);

}
