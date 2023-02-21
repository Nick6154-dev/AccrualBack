package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Type;

public interface TypeService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idType);

    ResponseEntity<?> save(Type type);

    ResponseEntity<?> delete(Type type);

    ResponseEntity<?> deleteById(Long idType);

    ResponseEntity<?> update(Type type, Long idType);

}
