package uce.edu.ec.accrualBack.repository;

import org.springframework.data.repository.CrudRepository;
import uce.edu.ec.accrualBack.entity.Docent;

import java.util.Optional;

public interface DocentRepository extends CrudRepository<Docent, Long> {

    Optional<Docent> findByIdPerson(Long idPerson);

}
