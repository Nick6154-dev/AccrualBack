package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.object.InstitutionActivity;

public interface InstitutionActivityService {

    ResponseEntity<?> save(InstitutionActivity institutionActivity);

    ResponseEntity<?> deleteById(Long idInstitution);

    ResponseEntity<?> update(InstitutionActivity institutionActivity, Long idInstitution);

}
