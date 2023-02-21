package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.UniversityInstitution;

public interface UniversityInstitutionService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idUniversityInstitution);

    ResponseEntity<?> findUniversityInstitutionByInstitution(Institution institution);

    ResponseEntity<?> save(UniversityInstitution universityInstitution);

    ResponseEntity<?> deleteById(Long idUniversityInstitution);

    ResponseEntity<?> update(UniversityInstitution universityInstitution, Long idUniversityInstitution);

}
