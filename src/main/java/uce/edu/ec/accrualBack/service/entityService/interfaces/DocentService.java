package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Docent;

import java.util.List;

public interface DocentService {

    List<Docent> findAll();

    List<Docent> findAllDocentSettlementNoApproved();

    Docent findById(Long idDocent);

    Docent findByIdPerson(Long idPerson);

    Docent save(Docent docent);

    String deleteById(Long idDocent);

    Docent updateAll(Docent docent, Long idDocent);

}
