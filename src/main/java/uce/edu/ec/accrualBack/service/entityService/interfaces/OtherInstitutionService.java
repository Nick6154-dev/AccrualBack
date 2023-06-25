package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.OtherInstitution;

import java.util.List;

public interface OtherInstitutionService {

    List<OtherInstitution> findAll();

    OtherInstitution findById(Long idOtherInstitution);

    OtherInstitution findOtherInstitutionByInstitution(Institution institution);

    OtherInstitution save(OtherInstitution otherInstitution);

    String deleteById(Long idOtherInstitution);

    void deleteOtherInstitutionByInstitution(Institution institution);

    OtherInstitution update(OtherInstitution otherInstitution, Long idOtherInstitution);

}
