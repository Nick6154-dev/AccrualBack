package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Subtype;
import uce.edu.ec.accrualBack.entity.Type;

import java.util.List;

public interface SubtypeService {

    List<Subtype> findAll();

    Subtype findById(Long idSubtype);

    List<Subtype> findSubtypesByType(Type type);

    Subtype save(Subtype subtype);

    String delete(Subtype subtype);

    String deleteById(Long idSubtype);

    Subtype update(Subtype subtype, Long idSubtype);

}
