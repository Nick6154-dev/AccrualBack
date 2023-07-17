package uce.edu.ec.accrualBack.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends CrudRepository<Plan, Long> {

    @Query("FROM Plan WHERE numberPlan = (SELECT MAX(p.numberPlan) FROM Plan p WHERE p.idDocent=:idDocent) AND idDocent=:idDocent")
    Optional<Plan> findNextNumberPlanByIdDocent(Long idDocent);

    Optional<Plan> findPlanByPeriodAndIdDocent(Period period, Long idDocent);

    Optional<List<Plan>> findPlansByIdDocent(Long idDocent);

    Optional<List<Plan>> findPlansByStateIs(Integer state);

    Optional<List<Plan>> findAllByEditableIsAndPeriod(Boolean editable, Period period);

}
