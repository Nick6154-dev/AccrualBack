package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.Period;

import java.util.List;

public interface PeriodService {

    List<Period> findAll();

    List<Period> findActivePeriodTrue();

    Period findById(Long idPeriod);

    Period save(Period period);

    String delete(Period period);

    String switchActivePeriod(Long idPeriod);

    Period update(Period period, Long idPeriod);

}
