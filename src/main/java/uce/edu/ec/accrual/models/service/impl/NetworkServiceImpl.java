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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Network> findAll() {
        return (List<Network>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Network findById(Long idNetwork) {
        return repository.findById(idNetwork).orElseGet(Network::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Network findByDocent(Docent docent) {
        return repository.findNetworkByDocent(docent).orElseGet(Network::new);
    }

    @Override
    @Transactional
    public Network save(Network network) {
        return Optional.ofNullable(findByDocent(network.getDocent())).orElseGet(() -> repository.save(network));
    }

    @Override
    @Transactional
    public String deleteById(Long idNetwork) {
        return repository.findById(idNetwork).map(value -> {
            repository.deleteById(idNetwork);
            return "Eliminado correctamente";
        }).orElseGet(() -> "Problemas al momento de eliminar");
    }

    @Override
    @Transactional
    public Network update(Network network, Long idNetwork) {
        return repository.findById(idNetwork).map(value -> {
            network.setIdNetworks(value.getIdNetworks());
            return repository.save(network);
        }).orElseGet(Network::new);
    }

}
