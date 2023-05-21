package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Docent;

import java.util.List;

public interface DocentService {

    List<Docent> findAll();

    Docent findById(Long idDocent);

    Docent findByIdPerson(Long idPerson);

    Docent save(Docent docent);

    String deleteById(Long idDocent);

    Docent updateAll(Docent docent, Long idDocent);

}
