package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Faculty;
import uce.edu.ec.accrual.models.entity.University;

import java.util.List;

public interface FacultyService {

    List<Faculty> findAll();

    Faculty findById(Long idFaculty);

    List<Faculty> findFacultiesByUniversity(University university);

}
