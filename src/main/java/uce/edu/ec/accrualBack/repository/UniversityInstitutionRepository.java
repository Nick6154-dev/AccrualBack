package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.UniversityInstitution;

import java.util.Optional;

public interface UniversityInstitutionRepository extends CrudRepository<UniversityInstitution, Long> {

    Optional<UniversityInstitution> findUniversityInstitutionByInstitution(Institution institution);

    void deleteUniversityInstitutionByInstitution(Institution institution);

}
