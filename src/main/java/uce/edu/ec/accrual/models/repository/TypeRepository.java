package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Type;

public interface TypeRepository extends CrudRepository<Type, Long> {
}
