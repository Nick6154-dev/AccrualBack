package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.University;

public interface UniversityRepository extends CrudRepository<University, Long> {
}
