package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Subtype;
import uce.edu.ec.accrual.models.entity.Type;

import java.util.List;
import java.util.Optional;

public interface SubtypeRepository extends CrudRepository<Subtype, Long> {

    Optional<List<Subtype>> findSubtypesByActivityType(Type type);

}
