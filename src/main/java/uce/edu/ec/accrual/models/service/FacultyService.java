package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.University;

public interface FacultyService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idFaculty);

    ResponseEntity<?> findFacultiesByUniversity(University university);

}
