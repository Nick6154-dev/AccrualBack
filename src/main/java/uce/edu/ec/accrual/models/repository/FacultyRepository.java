package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Faculty;
import uce.edu.ec.accrual.models.entity.University;

import java.util.List;

public interface FacultyRepository extends CrudRepository<Faculty, Long> {

    List<Faculty> findFacultiesByUniversity(University university);

}
