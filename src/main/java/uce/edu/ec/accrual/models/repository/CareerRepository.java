package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Career;
import uce.edu.ec.accrual.models.entity.Faculty;

import java.util.List;
import java.util.Optional;

public interface CareerRepository extends CrudRepository<Career, Long> {

    Optional<List<Career>> findCareersByFaculty(Faculty faculty);

}
