package uce.edu.ec.accrualBack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uce.edu.ec.accrualBack.entity.SettlementDocent;

public interface SettlementDocentRepository extends MongoRepository<SettlementDocent, String> {

    void deleteByIdDocent(Long idDocent);

    boolean existsByIdDocent(Long idDocent);

}
