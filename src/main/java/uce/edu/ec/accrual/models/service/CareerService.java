package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Faculty;

public interface CareerService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idCareer);

    ResponseEntity<?> findCareersByFaculty(Faculty faculty);

}
