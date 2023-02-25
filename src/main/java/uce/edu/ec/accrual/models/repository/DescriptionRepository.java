package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Description;

import java.util.Optional;

public interface DescriptionRepository extends CrudRepository<Description, Long> {

    Optional<Description> findDescriptionByActivityPlan(ActivityPlan activityPlan);

    void deleteByActivityPlan(ActivityPlan activityPlan);

}
