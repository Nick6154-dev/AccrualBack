package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;
import uce.edu.ec.accrual.models.repository.NetworkRepository;
import uce.edu.ec.accrual.models.service.NetworkService;

import java.util.Optional;

@Service
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of(repository.findAll()).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idNetwork) {
        return repository.findById(idNetwork).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findByDocent(Docent docent) {
        return repository.findNetworkByDocent(docent).map(value ->
                        ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Network network) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(network));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idNetwork) {
        return repository.findById(idNetwork).map(value -> {
            repository.deleteById(idNetwork);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Network network, Long idNetwork) {
        return repository.findById(idNetwork).map(value ->
                        ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(network)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

}
