package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.UniversityInstitution;

import java.util.List;

public interface UniversityInstitutionService {

    List<UniversityInstitution> findAll();

    UniversityInstitution findById(Long idUniversityInstitution);

    UniversityInstitution findUniversityInstitutionByInstitution(Institution institution);

    UniversityInstitution save(UniversityInstitution universityInstitution);

    String deleteById(Long idUniversityInstitution);

    UniversityInstitution update(UniversityInstitution universityInstitution, Long idUniversityInstitution);

}
