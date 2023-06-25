package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;

import java.util.Optional;

public interface NetworkRepository extends CrudRepository<Network, Long> {

    Optional<Network> findNetworkByDocent(Docent docent);

}
