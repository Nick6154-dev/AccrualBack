package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Person;

import java.util.List;
import java.util.Map;

public interface AccrualDataService {

    List<AccrualData> findAll();

    List<Person> findAllPeopleSettlementRequest();

    List<AccrualData> findAllByAccrualData(boolean settlement);

    AccrualData findById(Long idAccrualData);

    AccrualData findByDocent(Docent docent);

    Map<Integer, String> save(AccrualData accrualData, Long idPerson);

    String deleteById(Long idAccrualData);

    Map<Integer, String> update(AccrualData accrualData, Long idAccrualData);

    String updateObservations(String observations, Long idAccrualData);

    void updateSettlement(Boolean settlement, AccrualData accrualData);

    Map<Integer, String> requestApproval(Long idPerson);

    Map<Integer, String> approveSettlement(Long idPerson, boolean approved);

    Map<Integer, String> approveAllRequestSettlement();

}
