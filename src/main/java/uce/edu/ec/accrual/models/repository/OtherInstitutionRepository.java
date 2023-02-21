package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.entity.OtherInstitution;

import java.util.Optional;

public interface OtherInstitutionRepository extends CrudRepository<OtherInstitution, Long> {

    Optional<OtherInstitution> findOtherInstitutionByInstitution(Institution institution);

    void deleteOtherInstitutionByInstitution(Institution institution);

}
