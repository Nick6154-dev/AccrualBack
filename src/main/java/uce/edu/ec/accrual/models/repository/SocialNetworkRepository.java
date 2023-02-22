package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Network;
import uce.edu.ec.accrual.models.entity.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkRepository extends CrudRepository<SocialNetwork, Long> {

    Optional<SocialNetwork> findSocialNetworkByNetwork(Network network);

}
