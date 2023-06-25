package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.SocialNetwork;

import java.util.List;

public interface SocialNetworkService {

    List<SocialNetwork> findAll();

    SocialNetwork findById(Long idSocialNetwork);

    List<SocialNetwork> findByIdPerson(Long idPerson);

    SocialNetwork save(SocialNetwork socialNetwork);

    String deleteById(Long idSocialNetwork);

    SocialNetwork update(SocialNetwork socialNetwork, Long idSocialNetwork);

}
