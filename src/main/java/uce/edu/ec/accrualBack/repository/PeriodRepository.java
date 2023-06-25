package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodRepository extends CrudRepository<Period, Long> {

    Optional<Period> findPeriodByValuePeriod(String valuePeriod);

    List<Period> findPeriodByActive(Boolean active);

}
