package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.OtherInstitution;

import java.util.List;

public interface OtherInstitutionService {

    List<OtherInstitution> findAll();

    OtherInstitution findById(Long idOtherInstitution);

    OtherInstitution findOtherInstitutionByInstitution(Institution institution);

    OtherInstitution save(OtherInstitution otherInstitution);

    String deleteById(Long idOtherInstitution);

    OtherInstitution update(OtherInstitution otherInstitution, Long idOtherInstitution);

}
