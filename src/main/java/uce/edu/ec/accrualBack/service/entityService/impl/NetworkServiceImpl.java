package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;
import uce.edu.ec.accrualBack.entity.SocialNetwork;
import uce.edu.ec.accrualBack.repository.NetworkRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.NetworkService;

import java.util.*;

@Service
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkRepository repository;

    @Autowired
    private DocentService docentService;

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
    public Map<Integer, String> save(Network network, Long idPerson) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getIdDocent() == null) {
            response.put(400, "No se ha encontrado el docente en el sistema");
            return response;
        }
        network.setDocent(docent);
        network.setSocialNetworks(new ArrayList<>());
        repository.save(network);
        response.put(200, "Redes guardadas al docente exitosamente");
        return response;
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
    public Map<Integer, String> update(Network network, Long idNetwork) {
        Map<Integer, String> response = new HashMap<>();
        Network oldNetwork = findById(idNetwork);
        if (oldNetwork.getIdNetworks() == null) {
            response.put(400, "No existen redes asociadas");
            return response;
        }
        network.setIdNetworks(oldNetwork.getIdNetworks());
        network.setDocent(oldNetwork.getDocent());
        network.setSocialNetworks(oldNetwork.getSocialNetworks());
        repository.save(network);
        response.put(200, "Redes actualizadas exitosamente");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> updateSocialNetworks(List<SocialNetwork> socialNetworks, Long idNetworks) {
        Map<Integer, String> response = new HashMap<>();
        Network network = findById(idNetworks);
        if (network.getIdNetworks() == null) {
            response.put(400, "No existen redes asociadas");
            return response;
        }
        network.getSocialNetworks().addAll(socialNetworks);
        repository.save(network);
        response.put(200, "Redes sociales actualizadas en redes exitosamente");
        return response;
    }

}
