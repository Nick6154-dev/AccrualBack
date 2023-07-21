package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.SettlementDocent;

import java.util.List;

public interface SettlementDocentService {

    SettlementDocent findById(String idSettlementDocent);

    List<SettlementDocent> findAll();

    void save(SettlementDocent settlementDocent);

    void deleteByIdDocent(Long idDocent);

    boolean existsByIdDocent(Long idDocent);

}
