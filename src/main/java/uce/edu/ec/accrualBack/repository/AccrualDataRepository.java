package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;

import java.util.Optional;

public interface AccrualDataRepository extends CrudRepository<AccrualData, Long> {

    Optional<AccrualData> findAccrualDataByDocent(Docent docent);

}
