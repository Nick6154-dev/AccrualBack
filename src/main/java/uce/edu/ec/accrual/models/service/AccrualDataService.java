package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.AccrualData;
import uce.edu.ec.accrual.models.entity.Docent;

public interface AccrualDataService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idAccrualData);

    ResponseEntity<?> findByDocent(Docent docent);

    ResponseEntity<?> save(AccrualData accrualData);

    ResponseEntity<?> deleteById(Long idAccrualData);

    ResponseEntity<?> update(AccrualData accrualData, Long idAccrualData);

}
