package uce.edu.ec.accrualBack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uce.edu.ec.accrualBack.entity.StatePlan;

import java.util.Optional;

public interface StatePlanRepository extends MongoRepository<StatePlan, String> {

    boolean existsByIdPlan(Long idPlan);

    Optional<StatePlan> findByIdPlan(Long idPlan);

    void deleteByIdPlan(Long idPlan);

}
