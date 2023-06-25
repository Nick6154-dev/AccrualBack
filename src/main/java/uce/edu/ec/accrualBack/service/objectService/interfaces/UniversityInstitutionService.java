package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.UniversityInstitution;

import java.util.List;

public interface UniversityInstitutionService {

    List<UniversityInstitution> findAll();

    UniversityInstitution findById(Long idUniversityInstitution);

    UniversityInstitution findUniversityInstitutionByInstitution(Institution institution);

    UniversityInstitution save(UniversityInstitution universityInstitution);

    String deleteById(Long idUniversityInstitution);

    void deleteUniversityInstitutionByInstitution(Institution institution);

    UniversityInstitution update(UniversityInstitution universityInstitution, Long idUniversityInstitution);

}
