package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.ActivityPlan;

import java.util.List;
import java.util.Optional;

public interface ActivityPlanRepository extends CrudRepository<ActivityPlan, Long> {

    Optional<List<ActivityPlan>> findActivityPlansByIdPlan(Long idPlan);

    Optional<List<ActivityPlan>> findActivityPlansByState(Integer state);

}
