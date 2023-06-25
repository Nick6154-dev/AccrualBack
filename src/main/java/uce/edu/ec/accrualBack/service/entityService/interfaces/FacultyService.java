package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Faculty;
import uce.edu.ec.accrualBack.entity.University;

import java.util.List;

public interface FacultyService {

    List<Faculty> findAll();

    Faculty findById(Long idFaculty);

    List<Faculty> findFacultiesByUniversity(University university);

    Faculty save(Faculty faculty);

    String delete(Faculty faculty);

    String deleteById(Long idFaculty);

    Faculty update(Faculty faculty, Long idFaculty);

}
