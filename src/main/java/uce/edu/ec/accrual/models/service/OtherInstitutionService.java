package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.OtherInstitution;

public interface OtherInstitutionService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idOtherInstitution);

    ResponseEntity<?> findOtherInstitutionByInstitution(Institution institution);

    ResponseEntity<?> save(OtherInstitution otherInstitution);

    ResponseEntity<?> deleteById(Long idOtherInstitution);

    ResponseEntity<?> update(OtherInstitution otherInstitution, Long idOtherInstitution);

}
