package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Period;

import java.util.List;
import java.util.Map;

public interface PeriodService {

    List<Period> findAll();

    List<Period> findAllByIdPerson(Long idPerson);

    List<Map<String, Object>> findAllWithDetails();

    List<Period> findAllByUsername(String username);

    List<Period> findActivePeriodTrue();

    Period findById(Long idPeriod);

    Map<Integer, String> save(Period period);

    Map<Integer, String> deleteById(Long idPeriod);

    Map<Integer, String> switchActivePeriod(Long idPeriod);

    Map<Integer, String> switchStatePeriods(Map<String, Object> objects);

    Period update(Period period, Long idPeriod);

    boolean existsByValuePeriod(String valuePeriod);

}
