package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Institution;

public interface InstitutionService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idInstitution);

    ResponseEntity<?> findInstitutionByActivity(Long idActivity);

    ResponseEntity<?> save(Institution institution);

    ResponseEntity<?> deleteById(Long idInstitution);

    ResponseEntity<?> update(Institution institution, Long idInstitution);

}
