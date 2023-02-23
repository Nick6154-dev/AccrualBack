package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Faculty;
import uce.edu.ec.accrual.models.entity.University;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends CrudRepository<Faculty, Long> {

    List<Faculty> findFacultiesByUniversity(University university);

}
