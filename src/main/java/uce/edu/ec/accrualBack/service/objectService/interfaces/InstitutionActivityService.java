package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.object.InstitutionActivity;

public interface InstitutionActivityService {

    String save(InstitutionActivity institutionActivity);

    String deleteById(Long idInstitution);

    String update(InstitutionActivity institutionActivity, Long idInstitution);

}
