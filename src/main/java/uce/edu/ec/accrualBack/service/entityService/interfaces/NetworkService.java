package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;
import uce.edu.ec.accrualBack.entity.SocialNetwork;

import java.util.List;
import java.util.Map;

public interface NetworkService {

    List<Network> findAll();

    Network findById(Long idNetwork);

    Network findByDocent(Docent docent);

    Map<Integer, String> save(Network network, Long idPerson);

    String deleteById(Long idNetwork);

    Map<Integer, String> update(Network network, Long idNetwork);

    Map<Integer, String> updateSocialNetworks(List<SocialNetwork> socialNetworks, Long idNetworks);

}
