package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import uce.edu.ec.accrual.models.entity.SocialNetwork;

public interface SocialNetworkService {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long idSocialNetwork);

    ResponseEntity<?> findByIdPerson(Long idPerson);

    ResponseEntity<?> save(SocialNetwork socialNetwork);

    ResponseEntity<?> deleteById(Long idSocialNetwork);

    ResponseEntity<?> update(SocialNetwork socialNetwork, Long idSocialNetwork);

}
