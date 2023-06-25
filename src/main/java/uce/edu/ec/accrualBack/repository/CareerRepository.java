package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Career;
import uce.edu.ec.accrualBack.entity.Faculty;

import java.util.List;
import java.util.Optional;

public interface CareerRepository extends CrudRepository<Career, Long> {

    Optional<List<Career>> findCareersByFaculty(Faculty faculty);

}
