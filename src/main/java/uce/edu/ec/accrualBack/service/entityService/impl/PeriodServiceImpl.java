package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.PeriodDocent;
import uce.edu.ec.accrualBack.repository.PeriodRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodDocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PeriodService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PlanService;

import java.util.*;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository repository;

    @Autowired
    private PlanService planService;

    @Autowired
    private PeriodDocentService periodDocentService;

    @Autowired
    private DocentService  docentService;

    @Override
    @Transactional(readOnly = true)
    public List<Period> findAll() {
        return (List<Period>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findAllWithDetails() {
        List<Map<String, Object>> response = new ArrayList<>();
        List<Period> periods = (List<Period>) repository.findAll();
        for (Period period : periods) {
            Map<String, Object> map = new HashMap<>();
            map.put("period", period);
            map.put("numberDocentsInPeriod", periodDocentService.countAllByIdPeriod(period.getIdPeriod()));
            map.put("totalDocents", docentService.findAll().size());
            response.add(map);
        }
        return response;
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
            Map<Integer, Object> result = refactorStringValuePeriod(period.getValuePeriod());
            boolean flag = (boolean) result.get(1);
            if (flag) {
                int value1 = (int) result.get(2);
                int value2 = (int) result.get(3);
                boolean isEquals = true;
                int aux = value1;
                while (value2 >= value1) {
                    String newValuePeriod = value1 + "-" + aux;
                    Period periodAux = new Period();
                    periodAux.setValuePeriod(newValuePeriod);
                    if (isEquals) {
                        isEquals = false;
                        aux++;
                    } else {
                        isEquals = true;
                        value1 = aux;
                    }
                    if (this.existsByValuePeriod(newValuePeriod)) continue;
                    periodAux.setActive(true);
                    periodAux.setState(0);
                    repository.save(periodAux);
                }
                return new Period();
            } else {
                period.setActive(true);
                period.setState(0);
                return repository.save(period);
            }
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
    public String switchStatePeriod(Long idPeriod, Integer state, List<Long> docents) {
        return repository.findById(idPeriod).map(period -> {
            period.setState(state);
            period = repository.save(period);
            planService.setPlansEditableByPeriod(period);
            this.assignDocentsToPeriod(idPeriod, state, docents);
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

    @Override
    public boolean existsByValuePeriod(String valuePeriod) {
        return repository.existsByValuePeriod(valuePeriod);
    }

    private void assignDocentsToPeriod(Long idPeriod, Integer state, List<Long> docents) {
        for (Long idDocent : docents) {
            PeriodDocent periodDocent = new PeriodDocent();
            periodDocent.setIdDocent(idDocent);
            periodDocent.setIdPeriod(idPeriod);
            periodDocent.setState(state);
            periodDocentService.save(periodDocent);
        }
    }

    private Map<Integer, Object> refactorStringValuePeriod(String valuePeriod) {
        Map<Integer, Object> result = new HashMap<>();
        valuePeriod = valuePeriod.replace(" ", "");
        String[] valuePeriodByParts = valuePeriod.split("-");
        int value1 = Integer.parseInt(valuePeriodByParts[0]);
        int value2 = Integer.parseInt(valuePeriodByParts[1]);
        int total = value2 - value1;
        if (total > 1) {
            result.put(1, true);
            result.put(2, value1);
            result.put(3, value2);
            return result;
        }
        result.put(1, false);
        return result;
    }

}
