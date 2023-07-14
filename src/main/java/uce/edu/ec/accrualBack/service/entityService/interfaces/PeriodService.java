package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Period;

import java.util.List;

public interface PeriodService {

    List<Period> findAll();

    List<Period> findActivePeriodTrue();

    Period findById(Long idPeriod);

    Period save(Period period);

    String delete(Period period);

    String switchActivePeriod(Long idPeriod);

    String switchStatePeriod(Long idPeriod);

    Period update(Period period, Long idPeriod);

}
