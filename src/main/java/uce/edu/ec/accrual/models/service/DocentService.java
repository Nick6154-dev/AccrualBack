package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Docent;

public interface DocentService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idDocent);

    ResponseEntity<?> save(Docent docent);

    ResponseEntity<?> deleteById(Long idDocent);

    ResponseEntity<?> updateAll(Docent docent, Long idDocent);

}
