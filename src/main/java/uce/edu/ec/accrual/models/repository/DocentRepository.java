package uce.edu.ec.accrual.models.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrual.models.entity.Docent;

import java.util.Optional;

public interface DocentRepository extends CrudRepository<Docent, Long> {

    Optional<Docent> findByIdPerson(Long idPerson);

}
