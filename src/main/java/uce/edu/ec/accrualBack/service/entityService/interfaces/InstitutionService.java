package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Institution;

import java.util.List;

public interface InstitutionService {

    List<Institution> findAll();

    Institution findById(Long idInstitution);

    Institution findInstitutionByActivity(Long idActivity);

    Institution save(Institution institution);

    String deleteById(Long idInstitution);

    String delete(Institution institution);

    Institution update(Institution institution, Long idInstitution);

}
