package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Network;
import uce.edu.ec.accrualBack.entity.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkRepository extends CrudRepository<SocialNetwork, Long> {

    Optional<SocialNetwork> findSocialNetworkByNetwork(Network network);

}
