package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.SocialNetwork;

import java.util.List;
import java.util.Map;

public interface SocialNetworkService {

    List<SocialNetwork> findAll();

    SocialNetwork findById(Long idSocialNetwork);

    List<SocialNetwork> findByIdPerson(Long idPerson);

    Map<Integer, String> save(SocialNetwork socialNetwork, Long idPerson);

    String deleteById(Long idSocialNetwork);

    Map<Integer, String> update(SocialNetwork socialNetwork, Long idSocialNetwork);

}
