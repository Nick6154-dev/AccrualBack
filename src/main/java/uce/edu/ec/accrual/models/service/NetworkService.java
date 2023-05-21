package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;

import java.util.List;

public interface NetworkService {

    List<Network> findAll();

    Network findById(Long idNetwork);

    Network findByDocent(Docent docent);

    Network save(Network network);

    String deleteById(Long idNetwork);

    Network update(Network network, Long idNetwork);

}
