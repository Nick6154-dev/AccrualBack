package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;

public interface UniversityService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idUniversity);

}
