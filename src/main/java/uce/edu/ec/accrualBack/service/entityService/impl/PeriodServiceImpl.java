package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.repository.PeriodRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Period> findAll() {
        return (List<Period>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Period> findActivePeriodTrue() {
        return Optional.of(repository.findPeriodByActive(true)).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Period findById(Long idPeriod) {
        return repository.findById(idPeriod).orElseGet(Period::new);
    }

    @Override
    @Transactional
    public Period save(Period period) {
        return repository.findPeriodByValuePeriod(period.getValuePeriod()).orElseGet(() -> {
            period.setActive(true);
            period.setState(true);
            return repository.save(period);
        });
    }

    @Override
    @Transactional
    public String delete(Period period) {
        return repository.findById(period.getIdPeriod()).map(value -> {
            repository.delete(value);
            return "Eliminado con exito";
        }).orElseGet(() -> "No se ha encontrado el periodo seleccionado");
    }

    @Override
    @Transactional
    public String switchActivePeriod(Long idPeriod) {
        return repository.findById(idPeriod).map(period -> {
            period.setActive(!period.getActive());
            repository.save(period);
            return "Periodo cambido activo exitosamente";
        }).orElse("No se ha encontrado un periodo con ese id");
    }

    @Override
    @Transactional
    public String switchStatePeriod(Long idPeriod) {
        return repository.findById(idPeriod).map(period -> {
            period.setState(!period.getActive());
            repository.save(period);
            return "Periodo actualizado su estado exitosamente";
        }).orElse("No se ha encontrado el periodo por ese id");
    }

    @Override
    @Transactional
    public Period update(Period period, Long idPeriod) {
        return repository.findById(idPeriod).map(value -> {
            period.setIdPeriod(value.getIdPeriod());
            return repository.save(period);
        }).orElseGet(Period::new);
    }
}
