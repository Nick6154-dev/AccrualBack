package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.PeriodDocent;

import java.util.List;

public interface PeriodDocentService {

    PeriodDocent findById(Long idPeriodDocent);

    List<PeriodDocent> findAll();

    List<PeriodDocent> findAllByIdPeriod(Long idPeriod);

    List<PeriodDocent> findAllByIdDocent(Long idDocent);

    List<PeriodDocent> saveAll(List<PeriodDocent> periodDocents);

    PeriodDocent save(PeriodDocent periodDocent);

    void deleteById(Long idPeriodDocent);

    void delete(PeriodDocent periodDocent);

    PeriodDocent update(PeriodDocent periodDocent, Long idPeriodDocent);

    boolean existsByIdDocent(Long idDocent);

    boolean existsByIdPeriod(Long idPeriod);

    boolean existsByIdDocentAndIdPeriod(Long idDocent, Long idPeriod);

    Integer countAllByIdDocent(Long idDocent);

    Integer countAllByIdPeriod(Long idPeriod);

}
