package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.OtherInstitution;

import java.util.Optional;

public interface OtherInstitutionRepository extends CrudRepository<OtherInstitution, Long> {

    Optional<OtherInstitution> findOtherInstitutionByInstitution(Institution institution);

    void deleteOtherInstitutionByInstitution(Institution institution);

}
