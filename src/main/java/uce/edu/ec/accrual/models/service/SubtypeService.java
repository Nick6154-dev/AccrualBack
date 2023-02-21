package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Subtype;
import uce.edu.ec.accrual.models.entity.Type;

public interface SubtypeService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idSubtype);

    ResponseEntity<?> findSubtypeByType(Type type);

    ResponseEntity<?> save(Subtype description);

    ResponseEntity<?> delete(Subtype description);

    ResponseEntity<?> deleteById(Long idSubtype);

    ResponseEntity<?> update(Subtype description, Long idSubtype);

}
