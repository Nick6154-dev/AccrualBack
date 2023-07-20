package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Period;

import java.util.List;
import java.util.Map;

public interface PeriodService {

    List<Period> findAll();

    List<Map<String, Object>> findAllWithDetails();

    List<Period> findActivePeriodTrue();

    Period findById(Long idPeriod);

    Period save(Period period);

    String delete(Period period);

    String switchActivePeriod(Long idPeriod);

    String switchStatePeriod(Long idPeriod, Integer state, List<Long> docents);

    Period update(Period period, Long idPeriod);

    boolean existsByValuePeriod(String valuePeriod);

}
