package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Description;

public interface DescriptionService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idDescription);

    ResponseEntity<?> findDescriptionByActivityPlan(ActivityPlan activityPlan);

    ResponseEntity<?> save(Description description);

    ResponseEntity<?> delete(Description description);

    ResponseEntity<?> deleteById(Long idDescription);

    ResponseEntity<?> update(Description description, Long idDescription);

}
