package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;

import java.util.Optional;

public interface NetworkRepository extends CrudRepository<Network, Long> {

    Optional<Network> findNetworkByDocent(Docent docent);

}
