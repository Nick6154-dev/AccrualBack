package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;
import uce.edu.ec.accrual.models.entity.SocialNetwork;
import uce.edu.ec.accrual.models.repository.DocentRepository;
import uce.edu.ec.accrual.models.repository.NetworkRepository;
import uce.edu.ec.accrual.models.repository.SocialNetworkRepository;
import uce.edu.ec.accrual.models.service.SocialNetworkService;

import java.util.Optional;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {

    @Autowired
    private SocialNetworkRepository repository;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private DocentRepository docentRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idSocialNetwork) {
        return repository.findById(idSocialNetwork).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<?> findByIdPerson(Long idPerson) {
        Optional<Docent> docent = docentRepository.findByIdPerson(idPerson);
        if (docent.isPresent()) {
            Optional<Network> network = networkRepository.findNetworkByDocent(docent.get());
            if (network.isPresent()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(network.get().getSocialNetworks());
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha encontrado redes asociadas al docente");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha encontrado un docente asociado a ese id persona");
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(SocialNetwork socialNetwork) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(socialNetwork));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idSocialNetwork) {
        return repository.findById(idSocialNetwork).map(value -> {
            repository.deleteById(idSocialNetwork);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("ELiminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha podido eliminar"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(SocialNetwork socialNetwork, Long idSocialNetwork) {
        return repository.findById(idSocialNetwork).map(value ->
                        ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(socialNetwork)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
