package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;

public interface NetworkService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idNetwork);

    ResponseEntity<?> findByDocent(Docent docent);

    ResponseEntity<?> save(Network network);

    ResponseEntity<?> deleteById(Long idNetwork);

    ResponseEntity<?> update(Network network, Long idNetwork);

}
