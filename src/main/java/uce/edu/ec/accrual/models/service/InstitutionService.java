package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.Institution;

import java.util.List;

public interface InstitutionService {

    List<Institution> findAll();

    Institution findById(Long idInstitution);

    Institution findInstitutionByActivity(Long idActivity);

    Institution save(Institution institution);

    String deleteById(Long idInstitution);

    Institution update(Institution institution, Long idInstitution);

}
