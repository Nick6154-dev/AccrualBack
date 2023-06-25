package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;

import java.util.List;

public interface NetworkService {

    List<Network> findAll();

    Network findById(Long idNetwork);

    Network findByDocent(Docent docent);

    Network save(Network network);

    String deleteById(Long idNetwork);

    Network update(Network network, Long idNetwork);

}