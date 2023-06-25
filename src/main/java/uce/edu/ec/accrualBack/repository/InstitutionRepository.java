package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Institution;

import java.util.Optional;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {

    Optional<Institution> findInstitutionByIdActivity(Long idActivity);

}
