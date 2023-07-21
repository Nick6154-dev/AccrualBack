package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.PeriodDocent;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.repository.PeriodRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository repository;

    @Autowired
    private PlanService planService;

    @Autowired
    private PeriodDocentService periodDocentService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private PersonService personService;

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
    public List<Period> findAllByUsername(String username) {
        Person person = personService.findByEmail(username);
        Docent docent = docentService.findByIdPerson(person.getIdPerson());
        List<PeriodDocent> periodDocents = periodDocentService.findAllByIdDocent(docent.getIdDocent());
        List<Period> periods = new ArrayList<>();
        for (PeriodDocent periodDocent : periodDocents) {
            periods.add(this.findById(periodDocent.getIdPeriod()));
        }
        return periods;
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
    public Map<Integer, String> save(Period period) {
        Map<Integer, String> response = new HashMap<>();
        if (repository.findPeriodByValuePeriod(period.getValuePeriod()).isPresent()) {
            response.put(400, "El periodo ya existe!");
            return response;
        }
        Map<Integer, Object> refactoredValueData = refactorStringValuePeriod(period.getValuePeriod());
        boolean isRange = (boolean) refactoredValueData.get(1);
        if (isRange) {
            int startValue = (int) refactoredValueData.get(2);
            int endValue = (int) refactoredValueData.get(3);
            savePeriodRange(startValue, endValue);
            response.put(200, "El rango de periodos ha sido guardado exitosamente");
        } else {
            period.setActive(true);
            period.setState(0);
            response.put(200, "El periodo ha sido guardado exitosamente");
        }
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> deleteById(Long idPeriod) {
        Map<Integer, String> response = new HashMap<>();
        Optional<Period> optionalPeriod = repository.findById(idPeriod);
        if (optionalPeriod.isPresent()) {
            repository.delete(optionalPeriod.get());
            response.put(200, "Periodo eliminado exitosamente");
        } else {
            response.put(400, "No se ha encontrado el id especificado");
        }
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> switchActivePeriod(Long idPeriod) {
        Map<Integer, String> response = new HashMap<>();
        Optional<Period> optionalPeriod = repository.findById(idPeriod);
        if (optionalPeriod.isPresent()) {
            Period period = optionalPeriod.get();
            period.setActive(!period.getActive());
            repository.save(period);
            response.put(200, "Periodo in/activado exitosamente");
        } else {
            response.put(400, "No se ha encontrado el id especificado");
        }
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> switchStatePeriods(Map<String, Object> objects) {
        Map<Integer, String> response = new HashMap<>();
        Object periodsObject = objects.get("periods");
        List<Long> idPeriods;
        if (periodsObject instanceof List<?>) {
            List<?> periodsList = (List<?>) periodsObject;
            idPeriods = periodsList.stream()
                    .filter(obj -> obj instanceof Number)
                    .map(obj -> ((Number) obj).longValue())
                    .collect(Collectors.toList());
        } else {
            Integer aux = (Integer) periodsObject;
            idPeriods = Collections.singletonList(aux.longValue());
        }
        if (idPeriods.isEmpty()) {
            response.put(400, "Se necesita enviar al menos el id de un periodo para actualizar el estado del mismo");
            return response;
        }
        Integer state = (Integer) objects.get("state");
        if (state == null || state == 0) {
            response.put(400, "Se necesita enviar el estado a actualizar");
            return response;
        }
        List<Long> idDocents;
        if (state == 1) {
            idDocents = docentService.findAllDocentSettlementNoApproved()
                    .stream()
                    .map(Docent::getIdDocent)
                    .collect(Collectors.toList());
        } else {
            idDocents = (List<Long>) objects.get("docents");
        }
        if (idDocents.isEmpty()) {
            response.put(400, "Ha existido un error al cargar los ids de docentes");
            return response;
        }
        for (Long idPeriod : idPeriods) {
            Optional<Period> optionalPeriod = repository.findById(idPeriod);
            if (optionalPeriod.isPresent()){
                Period period = optionalPeriod.get();
                period.setState(state);
                period = repository.save(period);
                planService.setPlansEditableByPeriod(period);
                this.assignDocentsToPeriod(idPeriod, state, idDocents);
            } else {
                response.put(400, "No se ha encontrado el periodo con id: " + idPeriod + ", no se ha podido actualizar " +
                        "el estado de ese periodo");
            }
        }
        response.put(200, "Se ha actualizado el estado de el/los periodos enviados exitosamente");
        return response;
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
        Map<Integer, Object> refactoredValueData = new HashMap<>();
        valuePeriod = valuePeriod.replace(" ", "");
        String[] valuePeriodByParts = valuePeriod.split("-");
        int startValue = Integer.parseInt(valuePeriodByParts[0]);
        int endValue = Integer.parseInt(valuePeriodByParts[1]);
        int total = endValue - startValue;
        if (total > 1) {
            refactoredValueData.put(1, true);
            refactoredValueData.put(2, startValue);
            refactoredValueData.put(3, endValue);
            return refactoredValueData;
        }
        refactoredValueData.put(1, false);
        return refactoredValueData;
    }

    private void savePeriodRange(int startValue, int endValue) {
        int aux = startValue;
        boolean isEquals = true;
        while (endValue >= startValue) {
            String newValuePeriod = startValue + "-" + aux;
            if (!this.existsByValuePeriod(newValuePeriod)) {
                Period periodAux = new Period();
                periodAux.setValuePeriod(newValuePeriod);
                periodAux.setActive(true);
                periodAux.setState(0);
                repository.save(periodAux);
            }
            if (isEquals) {
                isEquals = false;
                aux++;
            } else {
                isEquals = true;
                startValue = aux;
            }
        }
    }

}
