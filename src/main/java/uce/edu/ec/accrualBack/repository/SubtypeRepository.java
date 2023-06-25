package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Subtype;
import uce.edu.ec.accrualBack.entity.Type;

import java.util.List;
import java.util.Optional;

public interface SubtypeRepository extends CrudRepository<Subtype, Long> {

    Optional<List<Subtype>> findSubtypesByActivityType(Type type);

}
