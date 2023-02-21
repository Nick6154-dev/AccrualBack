package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.AccrualData;
import uce.edu.ec.accrual.models.entity.Docent;

import java.util.Optional;

public interface AccrualDataRepository extends CrudRepository<AccrualData, Long> {

    Optional<AccrualData> findAccrualDataByDocent(Docent docent);

}
