package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Faculty;
import uce.edu.ec.accrualBack.entity.University;

import java.util.List;

public interface FacultyRepository extends CrudRepository<Faculty, Long> {

    List<Faculty> findFacultiesByUniversity(University university);

}
