package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.SettlementDocent;
import uce.edu.ec.accrualBack.repository.SettlementDocentRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.SettlementDocentService;

import java.util.List;

@Service
public class SettlementDocentServiceImpl implements SettlementDocentService {

    @Autowired
    private SettlementDocentRepository settlementDocentRepository;

    @Override
    @Transactional(readOnly = true)
    public SettlementDocent findById(String idSettlementDocent) {
        return settlementDocentRepository.findById(idSettlementDocent).orElse(new SettlementDocent(null));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SettlementDocent> findAll() {
        return settlementDocentRepository.findAll();
    }

    @Override
    public void save(SettlementDocent settlementDocent) {
        settlementDocentRepository.save(settlementDocent);
    }

    @Override
    @Transactional
    public void deleteByIdDocent(Long idDocent) {
        settlementDocentRepository.deleteByIdDocent(idDocent);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdDocent(Long idDocent) {
        return settlementDocentRepository.existsByIdDocent(idDocent);
    }
}
