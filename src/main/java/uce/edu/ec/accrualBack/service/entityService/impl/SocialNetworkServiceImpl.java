package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;
import uce.edu.ec.accrualBack.entity.SocialNetwork;
import uce.edu.ec.accrualBack.repository.SocialNetworkRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.NetworkService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.SocialNetworkService;

import java.util.*;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {

    @Autowired
    private SocialNetworkRepository repository;

    @Autowired
    private NetworkService networkService;

    @Autowired
    private DocentService docentService;

    @Override
    @Transactional(readOnly = true)
    public List<SocialNetwork> findAll() {
        return (List<SocialNetwork>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public SocialNetwork findById(Long idSocialNetwork) {
        return repository.findById(idSocialNetwork).orElseGet(SocialNetwork::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SocialNetwork> findByIdPerson(Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getIdDocent() != null) {
            Network network = networkService.findByDocent(docent);
            if (network.getIdNetworks() != null) {
                return network.getSocialNetworks();
            }
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public Map<Integer, String> save(SocialNetwork socialNetwork, Long idPerson) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getIdDocent() == null) {
            response.put(400, "Docente no encontrado en el sistema");
            return response;
        }
        Network network = networkService.findByDocent(docent);
        if (network.getIdNetworks() == null) {
            response.put(400, "No tiene cargado redes el docente indicado");
            return response;
        }
        socialNetwork.setNetwork(network);
        socialNetwork = repository.save(socialNetwork);
        networkService.updateSocialNetworks(Collections.singletonList(socialNetwork), network.getIdNetworks());
        response.put(200, "Red social guardada exitosamente");
        return response;
    }

    @Override
    @Transactional
    public String deleteById(Long idSocialNetwork) {
        return repository.findById(idSocialNetwork).map(value -> {
            repository.deleteById(idSocialNetwork);
            return "ELiminado con exito";
        }).orElse("No se ha podido eliminar");
    }

    @Override
    @Transactional
    public Map<Integer, String> update(SocialNetwork socialNetwork, Long idSocialNetwork) {
        Map<Integer, String> response = new HashMap<>();
        SocialNetwork oldSocialNetwork = findById(idSocialNetwork);
        if (oldSocialNetwork.getIdSocialNetwork() == null) {
            response.put(400, "No se ha encontrado la red social especificada");
            return response;
        }
        socialNetwork.setIdSocialNetwork(oldSocialNetwork.getIdSocialNetwork());
        socialNetwork.setNetwork(oldSocialNetwork.getNetwork());
        repository.save(socialNetwork);
        response.put(200, "Red social actualizada exitosamente");
        return response;
    }
}
