package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.PeriodDocent;

import java.util.List;

public interface PeriodDocentService {

    PeriodDocent findById(String idPeriodDocent);

    List<PeriodDocent> findAll();

    List<PeriodDocent> findAllByIdPeriod(Long idPeriod);

    List<PeriodDocent> findAllByIdDocent(Long idDocent);

    List<PeriodDocent> saveAll(List<PeriodDocent> periodDocents);

    PeriodDocent save(PeriodDocent periodDocent);

    void deleteById(String idPeriodDocent);

    void delete(PeriodDocent periodDocent);

    void deleteAllByIdDocent(Long idDocent);

    void deleteAllByIdPeriod(Long idPeriod);

    PeriodDocent update(PeriodDocent periodDocent, String idPeriodDocent);

    boolean existsByIdDocent(Long idDocent);

    boolean existsByIdPeriod(Long idPeriod);

    boolean existsByIdDocentAndIdPeriodAndState(Long idDocent, Long idPeriod, Integer State);

    boolean existsByIdDocentAndIdPeriod(Long idDocent, Long idPeriod);

    Integer countAllByIdDocent(Long idDocent);

    Integer countAllByIdPeriod(Long idPeriod);

}
