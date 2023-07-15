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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (docent != null) {
            Network network = networkService.findByDocent(docent);
            if (network != null) {
                return network.getSocialNetworks();
            }
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public SocialNetwork save(SocialNetwork socialNetwork) {
        return repository.save(socialNetwork);
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
    public SocialNetwork update(SocialNetwork socialNetwork, Long idSocialNetwork) {
        return repository.findById(idSocialNetwork).map(value -> repository.save(socialNetwork)).orElseGet(SocialNetwork::new);
    }
}
