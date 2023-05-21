package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.AccrualData;
import uce.edu.ec.accrual.models.entity.Docent;

import java.util.List;

public interface AccrualDataService {

    List<AccrualData> findAll();

    AccrualData findById(Long idAccrualData);

    AccrualData findByDocent(Docent docent);

    AccrualData save(AccrualData accrualData);

    String deleteById(Long idAccrualData);

    AccrualData update(AccrualData accrualData, Long idAccrualData);

    String updateObservations(String observations, Long idAccrualData);

    String updateSettlement(Boolean settlement, Long idPerson);

}
