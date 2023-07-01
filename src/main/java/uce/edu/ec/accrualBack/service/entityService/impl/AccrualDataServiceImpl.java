package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.repository.AccrualDataRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.AccrualDataService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccrualDataServiceImpl implements AccrualDataService {

    @Autowired
    private AccrualDataRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<AccrualData> findAll() {
        return (List<AccrualData>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public AccrualData findById(Long idAccrualData) {
        return repository.findById(idAccrualData).orElseGet(AccrualData::new);
    }

    @Override
    @Transactional(readOnly = true)
    public AccrualData findByDocent(Docent docent) {
        return repository.findAccrualDataByDocent(docent).orElseGet(AccrualData::new);
    }

    @Override
    @Transactional
    public AccrualData save(AccrualData accrualData) {
        return repository.findAccrualDataByDocent(accrualData.getDocent()).orElseGet(() -> repository.save(accrualData));
    }

    @Override
    @Transactional
    public String deleteById(Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> "Eliminado").orElseGet(() -> "No se ha podido eliminar");
    }

    @Override
    @Transactional
    public AccrualData update(AccrualData accrualData, Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> {
            accrualData.setIdAccrualData(value.getIdAccrualData());
            return repository.save(accrualData);
        }).orElseGet(AccrualData::new);
    }

    @Override
    @Transactional
    public String updateObservations(String observations, Long idAccrualData) {
        return repository.findById(idAccrualData).map(value -> {
            value.setObservations(observations);
            repository.save(value);
            return "Observacion guardada con exito";
        }).orElseGet(() -> "Observacion no guardada");
    }

    @Override
    @Transactional
    public String updateSettlement(Boolean settlement, Docent docent) {
        Optional<AccrualData> accrualData = repository.findAccrualDataByDocent(docent);
        if (accrualData.isPresent()) {
            accrualData.get().setSettlement(settlement);
            repository.save(accrualData.get());
            return "Finiquito actualizado";
        }
        return "Finiquito no actualizado";
    }

}
