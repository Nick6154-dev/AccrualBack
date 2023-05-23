package uce.edu.ec.accrual.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends CrudRepository<Plan, Long> {

    @Query("FROM Plan WHERE numberPlan = (SELECT MAX(p.numberPlan) FROM Plan p WHERE p.idDocent=:idDocent) AND idDocent=:idDocent")
    Optional<Plan> findNextNumberPlanByIdDocent(Long idDocent);

    @Query("FROM Plan p WHERE p.period.valuePeriod=:period AND p.idDocent=:idDocent")
    Optional<Plan> findByPeriodAndIdDocent(String period, Long idDocent);

    Optional<List<Plan>> findPlansByIdDocent(Long idDocent);

}
