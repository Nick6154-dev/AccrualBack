package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

}
