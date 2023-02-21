package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Activity;

public interface ActivityService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idActivity);

    ResponseEntity<?> save(Activity activity);

    ResponseEntity<?> delete(Activity activity);

    ResponseEntity<?> deleteById(Long idActivity);

    ResponseEntity<?> update(Activity activity, Long idActivity);

}
