package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.UniversityInstitution;

import java.util.Optional;

public interface UniversityInstitutionRepository extends CrudRepository<UniversityInstitution, Long> {

    Optional<UniversityInstitution> findUniversityInstitutionByInstitution(Institution institution);

    void deleteUniversityInstitutionByInstitution(Institution institution);

}
