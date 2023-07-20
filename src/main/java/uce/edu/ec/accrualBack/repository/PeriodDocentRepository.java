package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uce.edu.ec.accrualBack.entity.PeriodDocent;

import java.util.List;
import java.util.Optional;

public interface PeriodDocentRepository extends CrudRepository<PeriodDocent, Long> {

    List<PeriodDocent> findAllByIdDocent(Long idDocent);

    List<PeriodDocent> findAllByIdPeriod(Long idPeriod);

    boolean existsByIdDocent(Long idDocent);

    boolean existsByIdPeriod(Long idPeriod);

    Integer countAllByIdDocent(Long idDocent);

    Integer countAllByIdPeriod(Long idPeriod);

}
