package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.PeriodDocent;
import uce.edu.ec.accrualBack.repository.PeriodDocentRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodDocentService;

import java.util.List;

@Service
public class PeriodDocentServiceImpl implements PeriodDocentService {

    @Autowired
    private PeriodDocentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public PeriodDocent findById(String idPeriodDocent) {
        return repository.findById(idPeriodDocent).orElseGet(PeriodDocent::new);
    }

    @Override
    public List<PeriodDocent> findAll() {
        return (List<PeriodDocent>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeriodDocent> findAllByIdPeriod(Long idPeriod) {
        return repository.findAllByIdPeriod(idPeriod);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeriodDocent> findAllByIdDocent(Long idDocent) {
        return repository.findAllByIdDocent(idDocent);
    }

    @Override
    @Transactional
    public List<PeriodDocent> saveAll(List<PeriodDocent> periodDocents) {
        return (List<PeriodDocent>) repository.saveAll(periodDocents);
    }

    @Override
    @Transactional
    public PeriodDocent save(PeriodDocent periodDocent) {
        return repository.save(periodDocent);
    }

    @Override
    @Transactional
    public void deleteById(String idPeriodDocent) {
        if (repository.existsById(idPeriodDocent)) repository.deleteById(idPeriodDocent);
    }

    @Override
    @Transactional
    public void delete(PeriodDocent periodDocent) {
        if (repository.existsById(periodDocent.getIdPeriodDocent())) repository.delete(periodDocent);
    }

    @Override
    public void deleteAllByIdDocent(Long idDocent) {
        repository.deleteAllByIdDocent(idDocent);
    }

    @Override
    public void deleteAllByIdPeriod(Long idPeriod) {
        repository.deleteAllByIdPeriod(idPeriod);
    }

    @Override
    @Transactional
    public PeriodDocent update(PeriodDocent periodDocent, String idPeriodDocent) {
        if (repository.existsById(idPeriodDocent)) {
            periodDocent.setIdPeriodDocent(idPeriodDocent);
            return repository.save(periodDocent);
        }
        return new PeriodDocent();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdDocent(Long idDocent) {
        return repository.existsByIdDocent(idDocent);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdPeriod(Long idPeriod) {
        return repository.existsByIdPeriod(idPeriod);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdDocentAndIdPeriodAndState(Long idDocent, Long idPeriod, Integer state) {
        return repository.existsByIdDocentAndIdPeriodAndState(idDocent, idPeriod, state);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdDocentAndIdPeriod(Long idDocent, Long idPeriod) {
        return repository.existsByIdDocentAndIdPeriod(idDocent, idPeriod);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countAllByIdDocent(Long idDocent) {
        return repository.countAllByIdDocent(idDocent);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countAllByIdPeriod(Long idPeriod) {
        return repository.countAllByIdPeriod(idPeriod);
    }

}
