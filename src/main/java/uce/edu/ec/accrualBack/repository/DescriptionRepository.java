package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Description;

import java.util.Optional;

public interface DescriptionRepository extends CrudRepository<Description, Long> {

    Optional<Description> findDescriptionByActivityPlan(ActivityPlan activityPlan);

    void deleteByActivityPlan(ActivityPlan activityPlan);

}
